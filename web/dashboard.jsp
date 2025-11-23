<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Password Generator</title>
    <style>
        body { font-family: Arial; margin: 40px; background: #f4f4f4; }
        .container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .password { font-size: 24px; background: #eee; padding: 15px; margin: 20px 0; letter-spacing: 2px; }
        input[type=checkbox] { transform: scale(1.5); margin: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome, <%= session.getAttribute("user") != null ?
        ((com.passwordgen.model.User)session.getAttribute("user")).getUsername() : "Guest" %>!</h1>
    <h2>Generate Strong Password</h2>

    <form action="generate" method="post">
        <label>Length: <input type="number" name="length" min="8" max="50" value="16" required></label><br><br>

        <input type="checkbox" name="upper" id="upper" checked>
        <label for="upper">Include Uppercase</label><br>

        <input type="checkbox" name="numbers" id="numbers" checked>
        <label for="numbers">Include Numbers</label><br>

        <input type="checkbox" name="special" id="special" checked>
        <label for="special">Include Special Characters</label><br><br>

        <button type="submit" style="padding:10px 20px;font-size:16px;">Generate Password</button>
    </form>

    <% if (request.getAttribute("generatedPassword") != null) { %>
        <div class="password">
            <strong>Your Password:</strong><br>
            <%= request.getAttribute("generatedPassword") %>
        </div>
        <button onclick="navigator.clipboard.writeText('<%= request.getAttribute("generatedPassword") %>')">
            Copy to Clipboard
        </button>
    <% } %>

    <br><br>
    <a href="logout">Logout</a>
</div>
</body>
</html>