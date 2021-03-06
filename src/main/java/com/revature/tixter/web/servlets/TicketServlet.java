package com.revature.tixter.web.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.tixter.daos.TicketDAO;
import com.revature.tixter.exceptions.AuthenticationException;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.models.Tickets;
import com.revature.tixter.models.Users;
import com.revature.tixter.services.TicketService;
import com.revature.tixter.web.dtos.ErrorResponse;
import com.revature.tixter.web.dtos.TicketRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketServlet extends HttpServlet {
    private final TicketService ticketService;
    private final ObjectMapper mapper;

    public TicketServlet(TicketService TicService, ObjectMapper mapper) {
        this.ticketService = TicService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Tickets> ticketList = new ArrayList<>();

        String user_id = req.getParameter("user_id"); // 8080/ticket?user_id=

        try {
            if (user_id != null) {
                ticketList = ticketService.findByUserId(user_id);
            } else ticketList = ticketService.getAllTickets();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (ticketList.isEmpty()) {
            resp.setStatus(404);
            return;
        }

        String payload = mapper.writeValueAsString(ticketList);
        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter responseWriter = response.getWriter();
        response.setContentType("application/json");

        try {
            HttpSession currentSession = request.getSession(false);

            if (currentSession == null) {
                throw new AuthenticationException("No session found.");
            }

            Object authUserAttribute = currentSession.getAttribute("authUser");

            if (!(authUserAttribute instanceof Users)) {
                throw new InvalidRequestException("Unexpected type in session attribute");
            }

            TicketRequest newTicketRequest = mapper.readValue(request.getInputStream(), TicketRequest.class);
            Users session = (Users) authUserAttribute;
            newTicketRequest.setPublisher(session.getUser_id()); // get user id and store as ticket publisher

            ticketService.createNewTicket(newTicketRequest);

            response.setStatus(201);

        } catch (JsonParseException | JsonMappingException | InvalidRequestException e) {
            response.setStatus(400);
            ErrorResponse errorResp = new ErrorResponse(400, e);
            String payload = mapper.writeValueAsString(errorResp);
            responseWriter.write(payload);
        } catch (AuthenticationException e) {
            response.setStatus(401);
            ErrorResponse errorResp = new ErrorResponse(401, "No session found.", new AuthenticationException());
            String payload = mapper.writeValueAsString(errorResp);
            responseWriter.write(payload);
        } catch (Throwable t) {
            response.setStatus(500);
            System.out.println(Arrays.toString(t.getStackTrace()));
            ErrorResponse errorResp = new ErrorResponse(500, "An unexpected exception occurred. Please check the server logs", t);
            String payload = mapper.writeValueAsString(errorResp);
            responseWriter.write(payload);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketDAO ticketDAO = new TicketDAO();
        resp.setContentType("application/json");

        try {
            String ticket_id = req.getParameter("ticket_id"); // 8080/ticket?ticket_id=
            Tickets ticketExists = ticketDAO.findById(ticket_id);

            if (ticketExists != null) {
                ticketService.removeById(ticket_id);
                System.out.println("Ticket successfully deleted!");
                resp.setStatus(200);
            } else {
                System.out.println("Could not delete ticket! Check logs.");
                resp.setStatus(400);
            }
        } catch (IllegalAccessException e) {
            resp.setStatus(400);
            e.printStackTrace();
        } catch (InstantiationException e) {
            resp.setStatus(400);
            e.printStackTrace();
        }

    }


}
