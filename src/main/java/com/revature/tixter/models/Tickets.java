package com.revature.tixter.models;

import com.revature.tixter.models.Users;

public class Tickets {
    private String ticket_id;
    private Users publisher;
    private String name;
    private String start_time;
    private double price;
    private boolean available;
    private String movie_id;

    public Tickets(String name) {
        this.name = name;
    }

    public Tickets(String name, Users publisher) {
        this(name);
        this.publisher = publisher;
    }

    public Tickets() {
        super();
    }

    //___________________________Getter Section_________________________________
    public String getTicket_id() {return ticket_id;}
    public Users getPublisher() {return publisher;}
    public String getName() {return name;}
    public String getStart_time() {return start_time;}
    public double getPrice() {return price;}
    public boolean isAvailable() {return available;}
    public String getMovie_id() {return movie_id;}
    //___________________________Setter Section_________________________________
    public void setTicket_id(String ticket_id) {this.ticket_id = ticket_id;}
    public void setPublisher(Users publisher) {this.publisher = publisher;}
    public void setName(String name) {this.name = name;}
    public void setStart_time(String start_time) {this.start_time = start_time;}
    public void setPrice(double price) {this.price = price;}
    public void setAvailable(boolean available) {this.available = available;}
    public void setMovie_id(String movie_id) {this.movie_id = movie_id;}
}
