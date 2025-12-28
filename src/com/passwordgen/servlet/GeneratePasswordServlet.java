package com.passwordgen.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Random;

@WebServlet("/generate")
public class GeneratePasswordServlet extends HttpServlet {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS    = "0123456789";
    private static final String SPECIAL    = "!@#$%&*()_+-=[]{}|;':,.<>?";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // create session if needed, but DON'T force login
        HttpSession session = request.getSession();

        int length = Integer.parseInt(request.getParameter("length"));
        boolean includeUpper   = request.getParameter("upper") != null;
        boolean includeNumbers = request.getParameter("numbers") != null;
        boolean includeSpecial = request.getParameter("special") != null;

        StringBuilder pool = new StringBuilder(CHAR_LOWER);
        if (includeUpper)   pool.append(CHAR_UPPER);
        if (includeNumbers) pool.append(NUMBERS);
        if (includeSpecial) pool.append(SPECIAL);

        Random rnd = new Random();
        StringBuilder password = new StringBuilder();

        if (includeUpper) {
            password.append(CHAR_UPPER.charAt(rnd.nextInt(CHAR_UPPER.length())));
        }
        if (includeNumbers) {
            password.append(NUMBERS.charAt(rnd.nextInt(NUMBERS.length())));
        }
        if (includeSpecial) {
            password.append(SPECIAL.charAt(rnd.nextInt(SPECIAL.length())));
        }

        for (int i = password.length(); i < length; i++) {
            password.append(pool.charAt(rnd.nextInt(pool.length())));
        }

        char[] arr = password.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int j = rnd.nextInt(arr.length);
            char tmp = arr[i];
            arr[i]   = arr[j];
            arr[j]   = tmp;
        }

        request.setAttribute("generatedPassword", new String(arr));
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
