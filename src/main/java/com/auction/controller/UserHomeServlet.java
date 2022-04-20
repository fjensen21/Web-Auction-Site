package com.auction.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserHomeServlet", value = "/userhome")
public class UserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Make sure user is logged in
        if (request.getSession().getAttribute("user") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/user_home.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/mustlogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
