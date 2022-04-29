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

@WebServlet(name = "AuctionSearchServlet", value = "/auctionsearch")
public class AuctionSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int firstrow = 0;
        int rowcount = 3;
        AuctionDao auctionDao;
        List<AuctionContainer> auctionContainers;
        List<AuctionDetail> auctionDetails = new ArrayList<>();

        request.setAttribute("firstrow", firstrow);
        request.setAttribute("rowcount", rowcount);

        try {
            auctionDao = new AuctionDao();
            auctionContainers = auctionDao.getNextNAuctions(firstrow,rowcount);

            for(int i = 0; i < auctionContainers.size(); i++ ) {
                auctionDetails.add(new AuctionDetail(auctionContainers.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("auctions", auctionDetails);


        getServletContext().getRequestDispatcher("/WEB-INF/views/auctionsearch.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        AuctionDao auctionDao;
        List<AuctionContainer> auctionContainers;
        List<AuctionDetail> auctionDetails = new ArrayList<>();

        request.setAttribute("firstrow", firstrow);
        request.setAttribute("rowcount", rowcount);

        try {
            auctionDao = new AuctionDao();
            auctionContainers = auctionDao.getNextNAuctions(firstrow,rowcount);

            for(int i = 0; i < auctionContainers.size(); i++ ) {
                auctionDetails.add(new AuctionDetail(auctionContainers.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("auctions", auctionDetails);


        getServletContext().getRequestDispatcher("/WEB-INF/views/auctionsearch.jsp").forward(request, response);




    }
}
