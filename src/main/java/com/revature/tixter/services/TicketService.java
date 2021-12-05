package com.revature.tixter.services;

import com.revature.tixter.daos.TicketDAO;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.exceptions.ResourcePersistenceException;
import com.revature.tixter.models.Tickets;
import com.revature.tixter.web.dtos.NewTicketRequest;

public class TicketService {

    private final TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public boolean isTicketValid(Tickets ticket) {
        if (ticket == null) return false;
        if (ticket.getName().trim().equals("")) return false;
        return true;
    }

    public void createNewTicket(NewTicketRequest newTicketRequestRequest) {
        Tickets ticket = new Tickets();
        ticket.setName(newTicketRequestRequest.getName());
        ticket.setPublisher(newTicketRequestRequest.getPublisher());

        if (!isTicketValid(ticket)) {
            throw new InvalidRequestException("Invalid ticket values provided!");
        }

//        Tickets newTicket = ticketDAO.save(ticket);

//        if (newTicket == null) {
//            throw new ResourcePersistenceException("The ticket could not be persisted to the datasource!");
//        }

    }

}
