package com.revature.tixter.daos;

import com.revature.tixter.models.Users;
import com.revature.tixter.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserDAO implements CrudDAO {

    public Users findByEmail(String email) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from tixter_users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setUser_id(rs.getString("user_id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("user_age"));
                user.setType_id(rs.getInt("type_id"));
                user.setRole_id(rs.getInt("role_id"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public Users save(Users newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //Assign UUID to every tixter user
            newUser.setUser_id(UUID.randomUUID().toString());
            //prepare SQl statements
            String sql = "insert into tixter_users (user_id, firstname, lastname, email, password, user_age, type_id, role_id) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUser_id());
            pstmt.setString(2, newUser.getFirstname());
            pstmt.setString(3, newUser.getLastname());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getPassword());
            pstmt.setInt(6,newUser.getAge());
            pstmt.setInt(7,0);
            pstmt.setInt(8,0);
            //Execute SQL query and return new user
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 0) {return newUser;}
        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();

        }
        return null;
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
