package com.auction.controller;

import com.auction.dao.CustomerServiceRepDao;
import com.auction.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "CustomerServiceRepServlet", value = "/customerservicerep")
public class CustomerServiceRepServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CustomerServiceRepDao customerServiceRepDao = new CustomerServiceRepDao();
            ArrayList<String> messages = customerServiceRepDao.getAllUnansweredQuestions();
            request.setAttribute("messages", messages);
        } catch(SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/customerservicerep");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/customerrep_home.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String answer = request.getParameter("newanswer");
        String username = request.getParameter("username");
        int messagePairId = Integer.parseInt(request.getParameter("messagePairId"));
        CustomerServiceResponse customerServiceResponse = new CustomerServiceResponse();
        customerServiceResponse.set_messagePairId(messagePairId);
        customerServiceResponse.set_username(username);
        customerServiceResponse.set_response(answer);
        String question = "";
        try {
            CustomerServiceRepDao customerServiceRepDao = new CustomerServiceRepDao();
            question = customerServiceRepDao.questionOfInterest(customerServiceResponse.get_username(), customerServiceResponse.get_messagePairId());
        } catch(SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/customerservicerep");
        }
        customerServiceResponse.set_question(question);
        try {
            CustomerServiceRepDao customerServiceRepDao = new CustomerServiceRepDao();
            customerServiceRepDao.addQuestionReponsePair(customerServiceResponse);
        } catch(SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/customerservicerep");
        }
        try {
            CustomerServiceRepDao customerServiceRepDao = new CustomerServiceRepDao();
            customerServiceRepDao.deleteAnsweredQuestion(customerServiceResponse.get_username(), customerServiceResponse.get_messagePairId());
        } catch(SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/customerservicerep");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/customerrep_home.jsp").forward(request, response);
    }
}

