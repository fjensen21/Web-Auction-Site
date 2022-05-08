package com.auction.controller;

import com.auction.dao.AuctionDao;
import com.auction.model.AuctionContainer;
import com.auction.model.AuctionDetail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdvancedSearchServlet", value = "/advancedsearch")
public class AdvancedSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/advancedsearch.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicletype = request.getParameter("vehicletype");
        double lowest = -1;
        if(!request.getParameter("lowest").equals("")){
            lowest = Double.parseDouble(request.getParameter("lowest"));
        }

        double highest = -1;
        if(!request.getParameter("highest").equals("")){
            highest = Double.parseDouble(request.getParameter("highest"));
        }
        String colorkeyword = request.getParameter("color");

        AuctionDao auctionDao;
        List<AuctionDetail> auctionDetails = new ArrayList<>();
        try {
            auctionDao = new AuctionDao();
            List<AuctionContainer> auctionContainers = auctionDao.getAuctionsByCrit(lowest, highest, vehicletype, colorkeyword);
            for(int i = 0; i < auctionContainers.size(); i++ ) {
                auctionDetails.add(new AuctionDetail(auctionContainers.get(i)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("auctions", auctionDetails);
        getServletContext().getRequestDispatcher("/WEB-INF/views/advancedsearchresults.jsp").forward(request, response);
    }
}
