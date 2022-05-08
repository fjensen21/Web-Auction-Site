package com.auction.model;

public class CustomerServiceResponse {

    private int messagePairId;
    private String username;

    private String question;
    private String response;

    public void set_messagePairId(int messagePairId) { this.messagePairId = messagePairId; }
    public int get_messagePairId() { return messagePairId; }

    public void set_username(String username) { this.username = username; }
    public String get_username() { return username; }

    public void set_question(String question) { this.question = question; }
    public String get_question() { return question; }

    public void set_response(String response) { this.response = response; }
    public String get_response() { return response; }
}
