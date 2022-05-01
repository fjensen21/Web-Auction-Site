package com.auction.dao;

import com.auction.model.Auction;
import com.auction.model.Event;
import com.auction.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventDao {
    private Connection connection;

    public EventDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public void scheduleEvent(Event event) {
        String eventString = "CREATE EVENT IF NOT EXISTS ? " +
                "ON SCHEDULE ? DO " +
                "?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(eventString);
            preparedStatement.setString(1, event.getEventname());
            preparedStatement.setString(2, event.getSchedule());
            preparedStatement.setString(3, event.getSqlprocedure());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAuctionCloseEvent(Auction a) {
        Event e = new Event();
        e.setEventname("AuctionClose" + a.getAuction_id());
        e.setSchedule(a.getEnd_datetime());
        String eventString = String.format("CREATE EVENT IF NOT EXISTS %s ON SCHEDULE AT ? DO UPDATE auctions SET active=0 WHERE auction_id=?",e.getEventname());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(eventString);
            preparedStatement.setString(1, e.getSchedule());
            preparedStatement.setInt(2, a.getAuction_id());

            preparedStatement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

    }

}
