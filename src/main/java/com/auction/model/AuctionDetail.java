package com.auction.model;

public class AuctionDetail {
    private String vehicleDetails;
    private String userPosted;
    private int auction_id;

    public AuctionDetail(AuctionContainer ac) {
        Vehicle v = ac.getVehicle();
        Auction a = ac.getAuction();
        String detailString = v.getColor() + " " + v.getMake() + " " + v.getModel() + " " + v.getYear();
        this.vehicleDetails = detailString;
        this.userPosted = a.getUsername();
        this.auction_id = a.getAuction_id();
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getUserPosted() {
        return userPosted;
    }

    public void setUserPosted(String userPosted) {
        this.userPosted = userPosted;
    }

    public int getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(int auction_id) {
        this.auction_id = auction_id;
    }
}
