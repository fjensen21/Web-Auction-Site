package com.auction.controller;

import com.auction.dao.AuctionDao;
import com.auction.model.Auction;
import com.auction.model.User;
import com.auction.model.Vehicle;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AuctionCarServlet", value = "/auctioncar")
public class AuctionCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Make sure user is logged in
        if (request.getSession().getAttribute("user") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/auctioncar.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/mustlogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vin = request.getParameter("vin");
        User user = (User) request.getSession().getAttribute("user");

        AuctionDao auctionDao;
        try {
            auctionDao = new AuctionDao();
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
            e.printStackTrace();
            return;
        }

        if(auctionDao.vehicleIsListed(vin)) {
            // Reject auction posting
            request.setAttribute("errorMessage", "A vehicle with this vin is currently listed. Please wait for that auction to close");
            getServletContext().getRequestDispatcher("/WEB-INF/views/auctioncar.jsp").forward(request,response);
        } else {
            // Create auction, vehicle, post
            Vehicle v = new Vehicle();
            v.setVin(Integer.parseInt(request.getParameter("vin")));
            v.setMake(request.getParameter("make"));
            v.setModel(request.getParameter("model"));
            v.setYear(request.getParameter("year"));
            v.setColor(request.getParameter("color"));
            v.setNumdoors(Integer.parseInt(request.getParameter("numdoors")));
            v.setCar(true);
            v.setTruck(false);
            v.setMotorcycle(false);


            Auction a = new Auction();
            a.setVin(request.getParameter("vin"));
            a.setSecret_minimum(Double.parseDouble(request.getParameter("secretmin")));
            a.setIncrement(Double.parseDouble(request.getParameter("increment")));
            a.setInitial_price(Double.parseDouble(request.getParameter("initprice")));
            a.setActive(true);
            a.setUsername(user.getUsername());
            a.setEnd_datetime(request.getParameter("enddatetime"));
        }
    }
}
