package com.auction.model;

public class Vehicle {
    private int vin;
    private String make;
    private String model;
    private String year;
    private String color;
    private int numdoors;
    private int bed_size;
    private int pedal_size;
    private boolean isCar = false;
    private boolean isTruck = false;
    private boolean isMotorcycle = false;

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumdoors() {
        return numdoors;
    }

    public void setNumdoors(int numdoors) {
        this.numdoors = numdoors;
    }

    public int getBed_size() {
        return bed_size;
    }

    public void setBed_size(int bed_size) {
        this.bed_size = bed_size;
    }

    public int getPedal_size() {
        return pedal_size;
    }

    public void setPedal_size(int pedal_size) {
        this.pedal_size = pedal_size;
    }

    public boolean isCar() {
        return isCar;
    }

    public void setCar(boolean car) {
        isCar = car;
    }

    public boolean isTruck() {
        return isTruck;
    }

    public void setTruck(boolean truck) {
        isTruck = truck;
    }

    public boolean isMotorcycle() {
        return isMotorcycle;
    }

    public void setMotorcycle(boolean motorcycle) {
        isMotorcycle = motorcycle;
    }
}
