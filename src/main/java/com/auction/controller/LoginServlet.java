package com.auction.controller;

import com.auction.dao.UserDao;
import com.auction.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredUsername = request.getParameter("username"); // "testuser"
        String enteredPassword = request.getParameter("password"); // "testpass"

        UserDao userDao;
        HttpSession session = request.getSession();
        User user;

        try {
            userDao = new UserDao();
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
            e.printStackTrace();
            return;
        }

        if(userDao.validateCredentials(enteredUsername, enteredPassword)) {
            // Fetch user object for this user
            user = userDao.getUserByUsername(enteredUsername);
            session.setAttribute("user", user);
            if(user.isAdmin()) {
                // Admin home
            } else if (user.isCustomerRep()) {
                // customer rep home page
            } else {
                response.sendRedirect("/userhome");
            }

        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
}
