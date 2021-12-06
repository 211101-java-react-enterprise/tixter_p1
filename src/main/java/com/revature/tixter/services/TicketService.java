package com.revature.tixter.services;

import com.revature.nimble.OrmServiceDriver;
import com.revature.tixter.daos.TicketDAO;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.exceptions.ResourcePersistenceException;
import com.revature.tixter.models.Tickets;
import com.revature.tixter.web.dtos.TicketRequest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    private final TicketDAO ticketDAO;
    private final OrmServiceDriver orm = new OrmServiceDriver();

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public boolean isTicketValid(Tickets ticket) {
        if (ticket == null) return false;
        if (ticket.getName().trim().equals("")) return false;
        return true;
    }

    public void createNewTicket(TicketRequest newTicketRequest) throws IllegalAccessException, InstantiationException {

        Tickets ticket = new Tickets();
        ticket.setName(newTicketRequest.getName());
        ticket.setPublisher(newTicketRequest.getPublisher());
        ticket.setPrice(newTicketRequest.getPrice());
        ticket.setAvailable(newTicketRequest.getAvailable());
        ticket.setStart_time(newTicketRequest.getStart_time());
        ticket.setMovie_id(newTicketRequest.getMovie_id());

        if (!isTicketValid(ticket)) {
            throw new InvalidRequestException("Invalid ticket values provided!");
        }

        Tickets newTicket = ticketDAO.save(ticket);

        if (newTicket == null) {
            throw new ResourcePersistenceException("The ticket could not be persisted to the datasource!");
        }

    }

    public boolean updateTicketAvailability(String id, int newAvail) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        if (newAvail<0) {
            throw new InvalidRequestException("Please enter an availability number greater or equal to 0!");
        }

        boolean update = ticketDAO.updateAvailability(id, newAvail);

        return update;

    }

    public Tickets findByTicketId(String id) throws IllegalAccessException, InstantiationException {
        if (id == null || id.equals("")) {
            throw new InvalidRequestException("Invalid ticket id provided!");
        }

        return ticketDAO.findById(id);
    }

    public boolean removeById(String ticket_id) {
        return ticketDAO.removeById(ticket_id);
    }

    public List<Tickets> findByUserId(String user_id) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        return ticketDAO.findByPublisherId(user_id);
    }

    public List<Tickets> getAllTickets() throws IllegalAccessException, InstantiationException {
        return ticketDAO.findAllTickets();
    }
}
