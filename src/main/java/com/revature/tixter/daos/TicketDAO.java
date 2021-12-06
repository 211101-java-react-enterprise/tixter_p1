package com.revature.tixter.daos;

import com.revature.nimble.OrmServiceDriver;
import com.revature.tixter.models.Tickets;
import com.revature.tixter.models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TicketDAO implements CrudDAO {
    OrmServiceDriver orm = new OrmServiceDriver();

    public TicketDAO(){}
    public TicketDAO(OrmServiceDriver orm) {this.orm=orm;}

//    public Tickets save(Tickets ticket) {
//
//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            ticket.setTicket_id(UUID.randomUUID().toString());
//
//            String sql = "insert into tickets (ticket_id, publisher_id, ticket_name) values ( ?, ?, ?)";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, ticket.getTicket_id());
//            pstmt.setString(2, ticket.getPublisher());
//            pstmt.setString(3, ticket.getName());
//
//            int rowsInserted = pstmt.executeUpdate();
//
//            if (rowsInserted != 0) {
//                return ticket;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public Tickets save(Tickets newTicket) throws IllegalAccessException, InstantiationException {
        newTicket.setTicket_id(UUID.randomUUID().toString());
        return orm.creating(newTicket);

    }

    public Tickets update(Tickets newTicket ) {
//        return orm.update();
        return null;
    }

    public Tickets findById(String id) throws IllegalAccessException, InstantiationException {
        return orm.reading(Tickets.class, id);
    }

    public boolean removeById(String id) {
        return orm.delete(Tickets.class, id);
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
    public boolean update(Object updatedObj) {
        return false;
    }

//    @Override
//    public boolean removeById(String id) {
//        return false;
//    }
}