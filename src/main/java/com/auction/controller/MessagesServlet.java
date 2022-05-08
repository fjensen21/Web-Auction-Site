package com.auction.controller;

import com.auction.dao.MessageDao;
import com.auction.model.Message;
import com.auction.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MessagesServlet", value = "/messages")
public class MessagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        // Make sure user is logged in
        if (user == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/mustlogin.jsp").forward(request, response);
        }

        int firstrow = 0;
        int rowcount = 5;
        MessageDao messageDao;
        List<Message> messages = new ArrayList<>();

        request.setAttribute("firstrow", firstrow);
        request.setAttribute("rowcount", rowcount);

        try {
            messageDao = new MessageDao();
            messages = messageDao.getMessagesByUsername(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("messages", messages);


        getServletContext().getRequestDispatcher("/WEB-INF/views/messages.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int firstrow, rowcount;
        String page = request.getParameter("page");
        firstrow = Integer.parseInt(request.getParameter("firstrow"));
        rowcount = Integer.parseInt(request.getParameter("rowcount"));

        if(page.equals("next")) {
            firstrow = firstrow + rowcount;
        } else if(page.equals("previous")) {
            if(firstrow - rowcount >= 0) {
                firstrow = firstrow - rowcount;
            }
        }

        MessageDao messageDao;
        List<Message> messages = new ArrayList<>();

        request.setAttribute("firstrow", firstrow);
        request.setAttribute("rowcount", rowcount);

        try {
            messageDao = new MessageDao();
            messages = messageDao.getMessagesByUsername(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("messages", messages);


        getServletContext().getRequestDispatcher("/WEB-INF/views/messages.jsp").forward(request, response);
    }
}
