package com.auction.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private boolean isEndUser;
    private boolean isCustomerRep;
    private boolean isAdmin;

    public User() {
    }

    public boolean isEndUser() {
        return isEndUser;
    }

    public void setEndUser(boolean endUser) {
        isEndUser = endUser;
    }

    public boolean isCustomerRep() {
        return isCustomerRep;
    }

    public void setCustomerRep(boolean customerRep) {
        isCustomerRep = customerRep;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
