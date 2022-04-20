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
}
