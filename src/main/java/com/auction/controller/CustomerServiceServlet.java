package com.auction.controller;


import com.auction.dao.CustomerServiceDao;
import com.auction.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CustomerServiceServlet", value = "/customerservice")
public class CustomerServiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            CustomerServiceDao customerServiceDao = new CustomerServiceDao();
            ArrayList<String> messages = customerServiceDao.getPastUnansweredQuestions(user);
            request.setAttribute("messages", messages);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            CustomerServiceDao customerServiceDao = new CustomerServiceDao();
            ArrayList<String> messages = customerServiceDao.getAllAnsweredQuestions(user);
            request.setAttribute("qaPair", messages);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/customerservice.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (request.getParameter("clearUnanswered") != null && request.getParameter("clearUnanswered").equals("clear")) {
            System.out.println("Clear all text!");
            try {
                CustomerServiceDao customerServiceDao = new CustomerServiceDao();
                customerServiceDao.deleteUnansweredQuestions(user);
            } catch(SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/customerservice.jsp").forward(request, response);
            return;
        }
        else if (request.getParameter("clearAnswered") != null && request.getParameter("clearAnswered").equals("clear")) {
            System.out.println("Clear all text!");
            try {
                CustomerServiceDao customerServiceDao = new CustomerServiceDao();
                customerServiceDao.deleteAnsweredQuestions(user);
            } catch(SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/customerservice.jsp").forward(request, response);
            return;
        }
        String question = request.getParameter("newquestion");

        CustomerQuestion q = new CustomerQuestion();
        q.set_message(question);
        q.set_username(user.getUsername());
        try {
            CustomerServiceDao customerServiceDao = new CustomerServiceDao();
            customerServiceDao.addQuestion(q);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/customerservice.jsp").forward(request, response);
    }
}

