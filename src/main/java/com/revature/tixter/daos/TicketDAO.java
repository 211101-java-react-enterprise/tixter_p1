package com.revature.tixter.daos;

import com.revature.tixter.models.Tickets;
import com.revature.tixter.models.Users;
import com.revature.tixter.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TicketDAO implements CrudDAO {

    public Tickets save(Tickets ticket) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ticket.setTicket_id(UUID.randomUUID().toString());

            String sql = "insert into tickets (ticket_id, publisher_id, ticket_name) values ( ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ticket.getTicket_id());
            pstmt.setString(2, ticket.getPublisher().getUser_id());
            pstmt.setString(3, ticket.getName());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return ticket;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object save(Object newObj) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public boolean update(Object updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}