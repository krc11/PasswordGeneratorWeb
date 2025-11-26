package com.passwordgen.servlet;

import com.passwordgen.dao.UserDAO;
import com.passwordgen.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(username, password);

        if (userDAO.registerUser(user)) {
            resp.sendRedirect("login.html?reg=success");
        } else {
            resp.sendRedirect("register.html?error=1");
        }
    }
}
