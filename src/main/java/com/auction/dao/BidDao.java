package com.auction.dao;

import com.auction.model.Bid;
import com.auction.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BidDao {

    private Connection connection;

    public BidDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public void addBid(Bid b){
        String addQuery = "insert into bid(bid_datetime, username, auction_id, amount) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(addQuery);
            preparedStatement.setString(1, b.getBid_datetime());
            preparedStatement.setString(2, b.getUsername());
            preparedStatement.setInt(3, b.getAuction_id());
            preparedStatement.setDouble(4, b.getAmount());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bid getHighestBid(int auction_id) {
        String highestBidQuery = "select * from bid where bid.auction_id=? order by bid.amount DESC LIMIT 1";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(highestBidQuery);
            preparedStatement.setInt(1, auction_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Bid b = new Bid();
                b.setBid_datetime(rs.getString("bid_datetime"));
                b.setUsername(rs.getString("username"));
                b.setAuction_id(rs.getInt("auction_id"));
                b.setAmount(rs.getDouble("amount"));
                return b;
            } else {
                // There are no bids on this auction
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
