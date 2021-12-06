package com.revature.tixter.daos;

import com.revature.nimble.OrmServiceDriver;
import com.revature.tixter.models.Tickets;

import java.util.List;
import java.util.UUID;

public class TicketDAO implements CrudDAO {
    OrmServiceDriver orm = new OrmServiceDriver();

    public TicketDAO(){}
    public TicketDAO(OrmServiceDriver orm) {this.orm=orm;}

    public Tickets save(Tickets newTicket) throws IllegalAccessException, InstantiationException {
        newTicket.setTicket_id(UUID.randomUUID().toString());
        return orm.creating(newTicket);

    }

    public Tickets findById(String id) throws IllegalAccessException, InstantiationException {
        return orm.reading(Tickets.class, id);
    }

    public List<Tickets> findByPublisherId(String id) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        return orm.reading(Tickets.class,Tickets.class.getField("publisher_id"), id);
    }

    public boolean removeById(String id) {
        return orm.delete(Tickets.class, id);
    }

    public List findAllTickets() throws IllegalAccessException, InstantiationException {
        return orm.reading(Tickets.class);
    }

    public boolean updateAvailability(String id, int newAvail) throws NoSuchFieldException {
        return orm.update(Tickets.class,id,Tickets.class.getField("available"), newAvail);
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
}