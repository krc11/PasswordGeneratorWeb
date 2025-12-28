<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.passwordgen.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Password Generator</title>
    <style>
        body { font-family: Arial; margin: 40px; background: #f4f4f4; }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        .password {
            font-size: 24px;
            background: #eee;
            padding: 15px;
            margin: 20px 0;
            letter-spacing: 2px;
            word-break: break-all;
        }
        input[type=checkbox] { transform: scale(1.3); margin: 10px; }
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .top-bar a {
            text-decoration: none;
            color: #667eea;
            font-weight: bold;
        }
        .note {
            font-size: 13px;
            color: #555;
            margin-bottom: 15px;
        }
        button {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="top-bar">
        <h1 style="margin:0;">
            Welcome,
            <%
                User u = (User) session.getAttribute("user");
                boolean loggedIn = (u != null);
                out.print(loggedIn ? u.getUsername() : "Guest");
            %>!
        </h1>

        <div>
            <%
                if (loggedIn) {
            %>
                <a href="logout">Logout</a>
            <%
                } else {
            %>
                <a href="login.html">Login</a>
            <%
                }
            %>
        </div>
    </div>

    <p class="note">
        You can generate strong passwords
        <% if (!loggedIn) { %>
            as a guest. Your passwords are <strong>not saved</strong> on the server.
        <% } else { %>
            while logged in. They are still <strong>not stored</strong> anywhere.
        <% } %>
    </p>

    <h2>Generate Strong Password</h2>

    <form action="generate" method="post">
        <label>Length:
            <input type="number" name="length" min="8" max="50" value="16" required>
        </label><br><br>

        <input type="checkbox" name="upper" id="upper" checked>
        <label for="upper">Include Uppercase</label><br>

        <input type="checkbox" name="numbers" id="numbers" checked>
        <label for="numbers">Include Numbers</label><br>

        <input type="checkbox" name="special" id="special" checked>
        <label for="special">Include Special Characters</label><br><br>

        <button type="submit" style="padding:10px 20px;font-size:16px;">
            Generate Password
        </button>
    </form>

    <%
        String generated = (String) request.getAttribute("generatedPassword");
        if (generated != null) {
    %>
        <div class="password">
            <strong>Your Password:</strong><br>
            <%= generated %>
        </div>
        <button
            onclick="navigator.clipboard.writeText('<%= generated %>')"
            style="padding:8px 16px;">
            Copy to Clipboard
        </button>
    <% } %>

    <br><br>
    <a href="index.html">Back to Home</a>
</div>
</body>
</html>
