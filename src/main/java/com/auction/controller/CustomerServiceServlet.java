package com.auction.controller;

import com.auction.dao.AuctionDao;
import com.auction.dao.BidDao;
import com.auction.dao.CustomerServiceDao;
import com.auction.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServiceServlet", value = "/customerservice")
public class CustomerServiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            CustomerServiceDao customerServiceDao = new CustomerServiceDao();
            customerServiceDao.getPastQuestions(user);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    getServletContext().getRequestDispatcher("/WEB-INF/views/customerservice.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String question = request.getParameter("newquestion");
        User user = (User) request.getSession().getAttribute("user");
        // Create bid object
        CustomerQuestion q = new CustomerQuestion();
        q.set_message(question);
        q.set_username(user.getUsername());
        // Post bid to auction
        // TODO: Update auctions highest bidder if bid is new highest
        try {
            CustomerServiceDao customerServiceDao = new CustomerServiceDao();
            customerServiceDao.addQuestion(q);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/customerservice.jsp").forward(request, response);
    }
}

