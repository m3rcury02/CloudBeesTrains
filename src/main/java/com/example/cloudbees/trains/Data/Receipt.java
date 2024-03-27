package com.example.cloudbees.trains.Data;

public class Receipt {
    private String userId;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    private String from;
    private String to;
    private User user;
    private double pricePaid;
    private String seat;
    // getters and setters
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public double getPricePaid() {
        return pricePaid;
    }
    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }
    public String getSeat() {
        return seat;
    }
    public void setSeat(String seat) {
        this.seat = seat;
    }

}
