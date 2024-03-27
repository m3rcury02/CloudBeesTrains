package com.example.cloudbees.trains.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Ticket {
    @NotBlank(message = "From location is mandatory")
    private String from;

    @NotBlank(message = "To location is mandatory")
    private String to;

    @NotNull(message = "User is mandatory")
    private User user;

    @Positive(message = "Price should be positive")
    private double price;

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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

