package com.revature.tixter.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.tixter.models.Users;

public class NewTicketRequest {

    private String name;

    private String publisher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher() {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "NewCardRequest{" +
                "name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }

}