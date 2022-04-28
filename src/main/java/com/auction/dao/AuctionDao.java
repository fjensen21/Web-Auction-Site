package com.auction.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.auction.model.Auction;
import com.auction.util.DbUtil;


public class AuctionDao {
    private Connection connection;

    public AuctionDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public boolean vehicleIsListed(String vin) {
        // Checks if an auction exists with this vehicle and its datetime has yet to pass (maybe add closed attribute boolean to auction?)
        return false;
    }

    public boolean auctionIdExists(int id){
        // Given an integer checks if it is an id in the auctions table
        return false;
    }

    public void addAuction(Auction auction){
        // Create Auction row
        String addAuction = "insert into auctions(auction_id,highest_bidder_id,end_datetime)" +
                "values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(addAuction);

            preparedStatement.setInt(1, auction.getAuction_id());
            preparedStatement.setString(2, auction.getHighest_bidder_id());
            preparedStatement.setString(3, auction.getEnd_datetime());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        // Create post row
        String addPost = "insert into post(username,auction_id,post_datetime,vin,secret_minimum, increment, initial_price)" +
                "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(addPost);

            preparedStatement.setString(1, auction.getUsername());
            preparedStatement.setInt(2, auction.getAuction_id());
            preparedStatement.setString(3, auction.getPost_datetime());
            preparedStatement.setInt(4, auction.getVin());
            preparedStatement.setDouble(5,auction.getSecret_minimum());
            preparedStatement.setDouble(6, auction.getIncrement());
            preparedStatement.setDouble(7, auction.getInitial_price());
            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
