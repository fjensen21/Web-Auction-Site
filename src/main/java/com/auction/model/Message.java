package com.auction.model;

public class Message {
    private int id;
    private String username;
    private String message;
    private String msg_datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg_datetime() {
        return msg_datetime;
    }

    public void setMsg_datetime(String msg_datetime) {
        this.msg_datetime = msg_datetime;
    }
}
