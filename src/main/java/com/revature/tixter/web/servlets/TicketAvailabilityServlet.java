package com.revature.tixter.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.tixter.daos.TicketDAO;
import com.revature.tixter.models.Tickets;
import com.revature.tixter.services.TicketService;
import com.revature.tixter.web.dtos.TicketRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TicketAvailabilityServlet extends HttpServlet {
    private final TicketService ticketService;
    private final ObjectMapper mapper;

    public TicketAvailabilityServlet(TicketService TicService, ObjectMapper mapper) {
        this.ticketService = TicService;
        this.mapper = mapper;
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketDAO ticketDAO = new TicketDAO();

        resp.setContentType("application/json");

        try {
            String ticket_id = req.getParameter("ticket_id"); // 8080/ticket?ticket_id=
            Tickets ticketExists = ticketDAO.findById(ticket_id);

            if (ticketExists != null || ticket_id != null) {
                TicketRequest newTicketRequest = mapper.readValue(req.getInputStream(), TicketRequest.class);
                ticketService.updateTicketAvailability(ticket_id, newTicketRequest.getAvailable());

                System.out.println("Ticket successfully updated!");
                resp.setStatus(201);
            } else {
                System.out.println("Could not find ticket! Please end url with ?ticket_id=");
                resp.setStatus(500);
            }
        } catch (IllegalAccessException e) {
            resp.setStatus(400);
            e.printStackTrace();
        } catch (InstantiationException e) {
            resp.setStatus(400);
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
