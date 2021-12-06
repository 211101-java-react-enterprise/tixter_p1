package com.revature.tixter.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.nimble.annotations.Column;
import com.revature.tixter.models.Users;

public class TicketRequest {

    private String name;
    private String publisher;
    private double price;
    private String start_time;
    private int available;
    private String movie_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String toString() {
        return "NewCardRequest{" +
                "name='" + name + '\'' +
                ", price_id='" + price + '\'' +
                ", start_time='" + start_time + '\'' +
                ", available='" + available + '\'' +
                ", movie_id='" + movie_id + '\'' +
                '}';
    }

}