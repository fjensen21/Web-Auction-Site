package com.auction.model;

public class AuctionContainer {
    private Auction auction;
    private Vehicle vehicle;

    public AuctionContainer(Auction auction, Vehicle vehicle) {
        this.auction = auction;
        this.vehicle = vehicle;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
