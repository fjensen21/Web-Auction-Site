package com.auction.dao;

import com.auction.model.Bid;
import com.auction.model.CustomerQuestion;
import com.auction.model.User;
import com.auction.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class ReportsDao {
    private Connection connection;

    public ReportsDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    //todo: add numbers/earnings to return statement
    public String getBestBuyer() {
        String buyerQuery = "SELECT username, COUNT(username) AS `value_occurrence` FROM winner GROUP BY username ORDER BY `value_occurrence` DESC LIMIT 1";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(buyerQuery);
            ResultSet rs = preparedStatement.executeQuery();

            //return string here
            if(rs.next()) {
                String buyer;
                buyer = rs.getString("username");
                return buyer;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double getTotalEarnings() {
        String totalQuery = "SELECT auction_id FROM winner";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(totalQuery);
            ResultSet rs = preparedStatement.executeQuery();

            Double sum = 0.0;
            if(rs.next()){
                BidDao bidDao;
                bidDao = new BidDao();

                Bid b = bidDao.getHighestBid(rs.getInt("auction_id"));
                if(b!=null){
                    sum+=b.getAmount();
                }
            }

            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap getEarningsPerUser() {
        HashMap<String,Double> ht = new HashMap<String,Double>();
        String user;
        Double amt = 0.0;
        String userQuery = "select * from post,auctions where post.auction_id = auctions.auction_id and auctions.active=false";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(userQuery);
            ResultSet rs = preparedStatement.executeQuery();

            for(int i=0; i<100; i++){
                if(rs.next()){
                    amt=0.0;
                    BidDao bidDao;
                    bidDao = new BidDao();

                    Bid b = bidDao.getHighestBid(rs.getInt("auction_id"));
                    if (b!=null){
                        amt = b.getAmount();
                        user = rs.getString("username");
                        if(!ht.containsKey(user)){
                            ht.put(user, amt);
                        } else {
                            amt += ht.get(user);
                            ht.put(user,amt);
                        }
                    }else{
                        user = rs.getString("username");
                        if(!ht.containsKey(user)){
                            ht.put(user, amt);
                        }
                    }

                }
            }

            return ht;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getBestSellingItems() {
        try {
            ReportsDao reportsDao = new ReportsDao();

            ArrayList<Double> temp = reportsDao.getEarningsPerItemType();
            int index = -1;
            double max = 0;

            for(int i=0; i<temp.size(); i++){

                if (temp.get(i) > max) {
                    max = temp.get(i);
                    index = i;
                }
            }


            //what item is it
            String item;
            if(index==0){
                item = "car";
            }else if(index==1){
                item = "truck";
            }else{
                item = "motorbike";
            }
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Integer, Double> getEarningsPerItem(){
        String itemQuery = "SELECT p.auction_id,v.vin FROM auctions a, post p, vehicle v WHERE p.vin = v.vin and a.auction_id = p.auction_id";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(itemQuery);
            ResultSet rs = preparedStatement.executeQuery();

            HashMap<Integer,Double> ht = new HashMap<Integer, Double>();
            for(int i=0; i<30; i++){
                if(rs.next()){
                    Double amt=0.0;
                    BidDao bidDao;
                    bidDao = new BidDao();

                    Bid b = bidDao.getHighestBid(rs.getInt("auction_id"));

                    Integer vin = rs.getInt("vin");

                    if(b!=null){
                        if(!ht.containsKey(vin)){
                            amt=b.getAmount();
                            ht.put(vin,amt);
                        } else {
                            amt += ht.get(vin);
                            ht.put(vin,amt);
                        }
                    }else{
                        if(!ht.containsKey(vin)){
                            ht.put(vin,amt);
                        }
                    }
                }
            }

            return ht;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Double> getEarningsPerItemType() {
        //Position in array correspond to type sum
        // 0 = car, 1 = truck, 2 = motorbike
        ArrayList<Double> items = new ArrayList<Double>(3);

        String carQuery = "SELECT a.auction_id FROM auctions a, post p, vehicle v WHERE p.vin = v.vin and a.auction_id = p.auction_id AND v.isCar = true";
        String truckQuery = "SELECT a.auction_id FROM auctions a, post p, vehicle v WHERE p.vin = v.vin and a.auction_id = p.auction_id AND v.isTruck = true";
        String motorbikeQuery = "SELECT a.auction_id FROM auctions a, post p, vehicle v WHERE p.vin = v.vin and a.auction_id = p.auction_id AND v.isMotorBike = true";
        try {
            PreparedStatement preparedCarStatement =
                    connection.prepareStatement(carQuery);
            ResultSet rs = preparedCarStatement.executeQuery();

            PreparedStatement preparedTruckStatement =
                    connection.prepareStatement(truckQuery);
            ResultSet rs2 = preparedTruckStatement.executeQuery();

            PreparedStatement preparedMotorBikeStatement =
                    connection.prepareStatement(motorbikeQuery);
            ResultSet rs3 = preparedMotorBikeStatement.executeQuery();

            Double carSum = 0.0;
            Double truckSum = 0.0;
            Double motorbikeSum = 0.0;

            for(int i=0; i<25; i++){
                if(rs.next()){
                    BidDao bidDao;
                    bidDao = new BidDao();

                    Bid b = bidDao.getHighestBid(rs.getInt("auction_id"));
                    if(b!=null){
                        carSum += b.getAmount();
                    }
                }
                if(rs2.next()){
                    BidDao bidDao;
                    bidDao = new BidDao();

                    Bid b = bidDao.getHighestBid(rs2.getInt("auction_id"));
                    if(b!=null){
                        truckSum += b.getAmount();
                    }
                }
                if(rs3.next()){
                    BidDao bidDao;
                    bidDao = new BidDao();

                    Bid b = bidDao.getHighestBid(rs3.getInt("auction_id"));
                    if(b!=null){
                        motorbikeSum += b.getAmount();
                    }
                }
            }

            items.add(0,carSum);
            items.add(1,truckSum);
            items.add(2,motorbikeSum);

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
