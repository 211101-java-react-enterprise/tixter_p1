package com.revature.tixter.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.tixter.models.Users;

public class NewTicketRequest {

    private String name;

    @JsonIgnore
    private Users publisher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getPublisher() {
        return publisher;
    }

    public void setPublisher(Users publisher) {
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