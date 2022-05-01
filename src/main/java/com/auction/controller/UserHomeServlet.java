package com.auction.controller;

import com.auction.dao.AuctionDao;
import com.auction.model.AuctionContainer;
import com.auction.model.AuctionDetail;
import com.auction.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserHomeServlet", value = "/userhome")
public class UserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        // Make sure user is logged in
        if (user == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/mustlogin.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/user_home.jsp").forward(request, response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
