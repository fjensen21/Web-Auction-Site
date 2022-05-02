package com.auction.model;

import com.auction.dao.AuctionDao;

import java.sql.SQLException;
import java.util.Random;

public class Auction {
    private int auction_id;
    private String highest_bidder_id;
    private String end_datetime;

    private String username;
    private String post_datetime;
    private int vin;
    private double secret_minimum;
    private double increment;
    private double initial_price;
    private boolean active;

    private String winner;

    public Auction() {
        this.auction_id = generateUniqueAuctionId();
    }


    public static int generateUniqueAuctionId() {
        Random rand = new Random();
        int id = rand.nextInt(9999);
        AuctionDao auctionDao;

        try {
            auctionDao = new AuctionDao();
        } catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }
        while(auctionDao.auctionIdExists(id)){
            id = rand.nextInt(9999);
        }
        return id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(int auction_id) {
        this.auction_id = auction_id;
    }

    public String getHighest_bidder_id() {
        return highest_bidder_id;
    }

    public void setHighest_bidder_id(String highest_bidder_id) {
        this.highest_bidder_id = highest_bidder_id;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost_datetime() {
        return post_datetime;
    }

    public void setPost_datetime(String post_datetime) {
        this.post_datetime = post_datetime;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public double getSecret_minimum() {
        return secret_minimum;
    }

    public void setSecret_minimum(double secret_minimum) {
        this.secret_minimum = secret_minimum;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    public double getInitial_price() {
        return initial_price;
    }

    public void setInitial_price(double initial_price) {
        this.initial_price = initial_price;
    }
}
