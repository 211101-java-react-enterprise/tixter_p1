package com.revature.tixter.services;

import com.revature.tixter.models.Users;

public class userService {
    private Users sessionUser;

    public userService(){}
    public userService(Users sessionUser) {
        this.sessionUser = sessionUser;
    }

    //Check if registration information valid in the aspect of format
    public boolean isUserValid(Users TempUser) {
        if (TempUser == null) return false;
        if (TempUser.getFirstname() == null || TempUser.getFirstname().trim().equals("")) return false;
        if (TempUser.getLastname() == null || TempUser.getLastname().trim().equals("")) return false;
        if (TempUser.getEmail() == null || TempUser.getEmail().trim().equals("")) return false;
        if (TempUser.getPassword() == null || TempUser.getPassword().trim().equals("")) return false;
        if (TempUser.getAge()<=0 || TempUser.getAge()>120) return false;
        return true;
    }


}
