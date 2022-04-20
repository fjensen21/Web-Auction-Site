package com.auction.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChooseVehicleTypeServlet", value = "/choosevehicletype")
public class ChooseVehicleTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if user is logged in before sending them to the right page
        if (request.getSession().getAttribute("user") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/choosevehicletype.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/mustlogin.jsp").forward(request, response);
        }

        // ------ //
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
