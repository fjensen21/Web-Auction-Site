package com.auction.controller;

import com.auction.dao.BidDao;
import com.auction.model.Bid;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "BidHistoryServlet", value = "/viewbidhistory")
public class BidHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int auctionid = Integer.parseInt(request.getParameter("aucId"));

        BidDao bidDao;
        ArrayList<Bid> bids;

        try {
            bidDao = new BidDao();
            bids = bidDao.getBids(auctionid);
            request.setAttribute("bids", bids);
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/bidhistory.jsp").forward(request, response);
    }
}
