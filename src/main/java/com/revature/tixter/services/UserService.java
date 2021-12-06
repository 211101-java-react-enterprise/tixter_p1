package com.revature.tixter.services;

import com.revature.tixter.daos.UserDAO;
import com.revature.tixter.exceptions.AuthenticationException;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.exceptions.ResourcePersistenceException;
import com.revature.tixter.models.Users;

public class UserService {
    private UserDAO userDAO;

    public UserService(){}
    public UserService(UserDAO userDAO) {
        this.userDAO=userDAO;
    }

    //Check if registration information valid in the aspect of format
    public boolean isUserValid(Users TempUser) {
        if (TempUser == null) return false;
        if (TempUser.getFirstname() == null || TempUser.getFirstname().trim().equals("")) return false;
        if (TempUser.getLastname() == null || TempUser.getLastname().trim().equals("")) return false;
        if (TempUser.getEmail() == null || TempUser.getEmail().trim().equals("")) return false;
        if (TempUser.getPassword() == null || TempUser.getPassword().trim().equals("")) return false;
        if (TempUser.getAge()<=0) return false;
        return true;
    }

    //Register New Users
    public boolean register(Users TempUser) throws IllegalAccessException, InstantiationException {

        //first Check if Basic User Info Valid
        if (!isUserValid(TempUser))throw new InvalidRequestException("Invalid user data provided!");
        //Check if Email Occupied
        if (userDAO.findByEmail(TempUser.getEmail())!=null) {
            String msg = "Input Email Occupied, Please Enter Another Email Address";
            throw new ResourcePersistenceException(msg);
        }
        //Write new User Info to database
        Users newUser=userDAO.save(TempUser);
        //Check if return User exist
        if(newUser==null){return false;}
        return true;
    }

    public Users authenticateUser(String email, String password) throws IllegalAccessException, InstantiationException {
        //check if user input equals null or empty string at first place
        if (email == null || email.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }
        //Utilize DAO to get an instance of User
        Users authenticatedUser = userDAO.findUserByEmailAndPassword(email, password);
        //if DAO returns null instance, raise exception to tell client that email or password is wrong
        if (authenticatedUser == null) {
            throw new AuthenticationException("Email or password was wrong");
        }
        //otherwise, just return instance
        return authenticatedUser;

    }
}
