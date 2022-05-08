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
                sum += bidDao.getHighestBid(rs.getInt("auction_id")).getAmount();
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
        Double amt;
        String userQuery = "select * from post order by username";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(userQuery);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                BidDao bidDao;
                bidDao = new BidDao();
                System.out.println("auction id : "+bidDao.getHighestBid(rs.getInt("auction_id")).getAuction_id());
                amt = bidDao.getHighestBid(rs.getInt("auction_id")).getAmount();
                user = rs.getString("username");
                if(!ht.containsKey(rs.getString("username"))){
                    ht.put(user, amt);
                } else {
                    amt += ht.get(user);
                    ht.put(user,amt);
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
            double[] temp = reportsDao.getEarningsPerItemType();
            int index = -1;
            double max = 0;
            for(int i=0; i<temp.length; i++){
                if (temp[i] > max) {
                    max = temp[i];
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
        String itemQuery = "SELECT a.auction_id FROM auction a , post p , vin v WHERE p.vin = v.vin, a.auction_id = p.auction ID";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(itemQuery);
            ResultSet rs = preparedStatement.executeQuery();

            HashMap<Integer,Double> ht = new HashMap<Integer, Double>();
            if(rs.next()){
                Double amt=0.0;
                BidDao bidDao;
                bidDao = new BidDao();
                amt = bidDao.getHighestBid(rs.getInt("auction_id")).getAmount();

                Integer vin = rs.getInt("vin");
                if(!ht.containsKey(vin)){
                    ht.put(vin,amt);
                } else {
                    amt += ht.get(vin);
                    ht.put(vin,amt);
                }
            }

            return ht;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public double[] getEarningsPerItemType() {
        //Position in array correspond to type sum
        // 0 = car, 1 = truck, 2 = motorbike
        double[] items = new double[3];

        String carQuery = "SELECT a.auction_id FROM auction a , post p , vin v WHERE p.vin = v.vin, a.auction_id = p.auction ID AND v.isCar = 1";
        String truckQuery = "SELECT a.auction_id FROM auction a , post p , vin v WHERE p.vin = v.vin, a.auction_id = p.auction ID AND v.isTruck = 1";
        String motorbikeQuery = "SELECT a.auction_id FROM auction a , post p , vin v WHERE p.vin = v.vin, a.auction_id = p.auction ID AND v.isMotorBike = 1";
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
            Double motorbikeSum =0.0;
            if(rs.next()){
                BidDao bidDao;
                bidDao = new BidDao();
                carSum += bidDao.getHighestBid(rs.getInt("auction_id")).getAmount();
            }
            if(rs2.next()){
                BidDao bidDao;
                bidDao = new BidDao();
                truckSum += bidDao.getHighestBid(rs.getInt("auction_id")).getAmount();
            }
            if(rs3.next()){
                BidDao bidDao;
                bidDao = new BidDao();
                motorbikeSum += bidDao.getHighestBid(rs.getInt("auction_id")).getAmount();
            }

            items[0] = carSum;
            items[1] = truckSum;
            items[2] = motorbikeSum;
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
