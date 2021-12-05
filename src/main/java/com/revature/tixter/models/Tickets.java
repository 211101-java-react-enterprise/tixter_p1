package com.revature.tixter.models;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;
import com.revature.tixter.models.Users;

@Table(tableName = "tickets")
public class Tickets {
    @Key(keyName = "ticket_id")
    public String ticket_id;
    @Column(columnName = "publisher_id")
    public String publisher;
    @Column(columnName = "ticket_name")
    public String name;
    @Column(columnName = "start_time")
    public String start_time;
    @Column(columnName = "price")
    public double price;
    @Column(columnName = "available")
    public int available;
    @Column(columnName = "movie_id")
    public String movie_id;

    public Tickets(String name) {
        this.name = name;
    }

    public Tickets(String name, String publisher) {
        this(name);
        this.publisher = publisher;
    }

    public Tickets() {
        super();
    }

    //___________________________Getter Section_________________________________
    public String getTicket_id() {return ticket_id;}
    public String getPublisher() {return publisher;}
    public String getName() {return name;}
    public String getStart_time() {return start_time;}
    public double getPrice() {return price;}
    public int getAvailable() {return available;}
    public String getMovie_id() {return movie_id;}
    //___________________________Setter Section_________________________________
    public void setTicket_id(String ticket_id) {this.ticket_id = ticket_id;}
    public void setPublisher(String publisher) {this.publisher = publisher;}
    public void setName(String name) {this.name = name;}
    public void setStart_time(String start_time) {this.start_time = start_time;}
    public void setPrice(double price) {this.price = price;}
    public void setAvailable(int available) {this.available = available;}
    public void setMovie_id(String movie_id) {this.movie_id = movie_id;}
}
