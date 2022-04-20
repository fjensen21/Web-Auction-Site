package com.auction.controller;

import com.auction.dao.UserDao;
import com.auction.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

@WebServlet(name = "CreateAccountServlet", value = "/createaccount")
public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/createaccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredUsername = request.getParameter("username");
        UserDao userDao;

        try {
            userDao = new UserDao();
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
            e.printStackTrace();
            return;
        }

        if (userDao.isUniqueUsername(enteredUsername)) {
            // Create account for user
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setFirst_name(request.getParameter("firstname"));
            user.setLast_name(request.getParameter("lastname"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setEndUser(true);
            user.setAdmin(false);
            user.setCustomerRep(false);
            userDao.addUser(user);

            response.sendRedirect("/index.jsp");

        } else {
            // Reject account creation
            request.setAttribute("errorMessage", "That username is taken");
            getServletContext().getRequestDispatcher("/WEB-INF/views/createaccount.jsp").forward(request, response);
        }
    }
}
