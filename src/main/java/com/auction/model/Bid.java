package com.auction.model;

public class Bid {
    private String bid_datetime;
    private String username;
    private int auction_id;
    private double amount;

    public Bid() {
    }

    public String getBid_datetime() {
        return bid_datetime;
    }

    public void setBid_datetime(String bid_datetime) {
        this.bid_datetime = bid_datetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(int auction_id) {
        this.auction_id = auction_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
