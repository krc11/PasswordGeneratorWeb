package com.passwordgen.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/generate")
public class GeneratePasswordServlet extends HttpServlet {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL = "!@#$%&*()_+-=[]{}|;':,.<>?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("web/login.html");
            return;
        }

        int length = Integer.parseInt(request.getParameter("length"));
        boolean includeUpper = request.getParameter("upper") != null;
        boolean includeNumbers = request.getParameter("numbers") != null;
        boolean includeSpecial = request.getParameter("special") != null;

        String pool = CHAR_LOWER;
        if (includeUpper) pool += CHAR_UPPER;
        if (includeNumbers) pool += NUMBERS;
        if (includeSpecial) pool += SPECIAL;

        StringBuilder password = new StringBuilder();
        java.util.Random rnd = new java.util.Random();

        // Guarantee at least one of each selected type (good design!)
        if (includeUpper) password.append(CHAR_UPPER.charAt(rnd.nextInt(CHAR_UPPER.length())));
        if (includeNumbers) password.append(NUMBERS.charAt(rnd.nextInt(NUMBERS.length())));
        if (includeSpecial) password.append(SPECIAL.charAt(rnd.nextInt(SPECIAL.length())));

        for (int i = password.length(); i < length; i++) {
            password.append(pool.charAt(rnd.nextInt(pool.length())));
        }

        // Shuffle
        char[] arr = password.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int j = rnd.nextInt(arr.length);
            char temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
        }

        request.setAttribute("generatedPassword", new String(arr));
        request.getRequestDispatcher("web/dashboard.jsp").forward(request, response);
    }
}