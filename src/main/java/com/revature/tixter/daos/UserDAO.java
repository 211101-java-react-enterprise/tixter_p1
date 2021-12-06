package com.revature.tixter.daos;

import com.revature.nimble.OrmServiceDriver;
import com.revature.tixter.exceptions.AuthenticationException;
import com.revature.tixter.models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserDAO implements CrudDAO {
    //Instantiate an ORM driver to simplify data layer logic
    OrmServiceDriver orm = new OrmServiceDriver();

    public UserDAO(){}
    public UserDAO(OrmServiceDriver o){orm=o;}

    public Users save(Users newUser) throws IllegalAccessException, InstantiationException {
        //Assign UUID to every tixter user
        newUser.setUser_id(UUID.randomUUID().toString());
        //call orm to persist new user info
        return orm.creating(newUser);
    }

    public Users findUserByEmailAndPassword(String email, String password) throws IllegalAccessException, InstantiationException {
        //utilizing findByEmail to get Users object
        Users authUser=findByEmail(email);
        if(authUser!=null) {//if user occur verify if password matches
            if (authUser.getPassword().equals(password)) {
                return authUser;
            }//if password doesn't match return null
            else return null;
        }
        else {//if no such user, return null
            return null;
        }
    }

    public Users findByEmail(String email) throws IllegalAccessException, InstantiationException {
        return orm.reading(Users.class, email);
    }

    @Override
    public Object save(Object newObj) {return null;}

    @Override
    public List findAll() {return null;}

    @Override
    public Object findById(String id) {return null;}

    @Override
    public boolean update(Object updatedObj) {return false;}

    @Override
    public boolean removeById(String id) {return false;}
}
