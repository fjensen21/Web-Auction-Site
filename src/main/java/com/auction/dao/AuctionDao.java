package com.auction.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.auction.model.Auction;
import com.auction.model.AuctionContainer;
import com.auction.model.Vehicle;
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
        String addAuction = "insert into auctions(auction_id,highest_bidder_id,end_datetime,active)" +
                "values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(addAuction);

            preparedStatement.setInt(1, auction.getAuction_id());
            preparedStatement.setString(2, auction.getHighest_bidder_id());
            preparedStatement.setString(3, auction.getEnd_datetime());
            preparedStatement.setBoolean(4, auction.isActive());
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


    public AuctionContainer getAuctionContainerByAuctionId(int auctionId){
        String getAuction = "select * from vehicle, auctions, post where auctions.auction_id=post.auction_id and vehicle.vin=post.vin and auctions.auction_id=?";
        AuctionContainer ac;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAuction);
            preparedStatement.setInt(1, auctionId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Auction auction = new Auction();
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(rs.getInt("vin"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getString("year"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setNumdoors(rs.getInt("num_doors"));
                vehicle.setBed_size(rs.getInt("bed_size"));
                vehicle.setPedal_size(rs.getInt("pedal_size"));
                vehicle.setCar(rs.getBoolean("isCar"));
                vehicle.setTruck(rs.getBoolean("isTruck"));
                vehicle.setMotorcycle(rs.getBoolean("isMotorBike"));

                auction.setAuction_id(rs.getInt("auction_id"));
                auction.setHighest_bidder_id(rs.getString("highest_bidder_id"));
                auction.setEnd_datetime(rs.getString("end_datetime"));
                auction.setActive(rs.getBoolean("active"));
                auction.setUsername(rs.getString("username"));
                auction.setAuction_id(rs.getInt("auction_id"));
                auction.setPost_datetime(rs.getString("post_datetime"));
                auction.setVin(rs.getInt("vin"));
                auction.setSecret_minimum(rs.getDouble("secret_minimum"));
                auction.setIncrement(rs.getDouble("increment"));
                auction.setInitial_price(rs.getDouble("initial_price"));

                ac = new AuctionContainer(auction, vehicle);
                return ac;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<AuctionContainer> getNextNAuctions(int firstrow, int rowcount){
        String getAuctions = "select * from vehicle, auctions, post where auctions.auction_id=post.auction_id and vehicle.vin=post.vin LIMIT ? OFFSET ?;";
        List<AuctionContainer> auctionContainers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(getAuctions);
            preparedStatement.setInt(1, rowcount);
            preparedStatement.setInt(2, firstrow);

            ResultSet rs = preparedStatement.executeQuery();

            // For each result in results set create auction and vehicle then add to auctionContainer than add to auctionContainers
            while(rs.next()) {
                Auction auction = new Auction();
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(rs.getInt("vin"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getString("year"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setNumdoors(rs.getInt("num_doors"));
                vehicle.setBed_size(rs.getInt("bed_size"));
                vehicle.setPedal_size(rs.getInt("pedal_size"));
                vehicle.setCar(rs.getBoolean("isCar"));
                vehicle.setTruck(rs.getBoolean("isTruck"));
                vehicle.setMotorcycle(rs.getBoolean("isMotorBike"));

                auction.setAuction_id(rs.getInt("auction_id"));
                auction.setHighest_bidder_id(rs.getString("highest_bidder_id"));
                auction.setEnd_datetime(rs.getString("end_datetime"));
                auction.setActive(rs.getBoolean("active"));
                auction.setUsername(rs.getString("username"));
                auction.setAuction_id(rs.getInt("auction_id"));
                auction.setPost_datetime(rs.getString("post_datetime"));
                auction.setVin(rs.getInt("vin"));
                auction.setSecret_minimum(rs.getDouble("secret_minimum"));
                auction.setIncrement(rs.getDouble("increment"));
                auction.setInitial_price(rs.getDouble("initial_price"));

                AuctionContainer ac = new AuctionContainer(auction, vehicle);
                auctionContainers.add(ac);
            }

        } catch (SQLException e) {
            System.out.println("caught");
            e.printStackTrace();
        }
        return auctionContainers;
    }

    public void updateHighestBid(int auctionid, String newHighestBidder){
        String update = "update auctions set highest_bidder_id =? where auction_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, newHighestBidder);
            preparedStatement.setInt(2, auctionid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
