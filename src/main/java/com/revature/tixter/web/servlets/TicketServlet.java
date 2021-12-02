package com.revature.tixter.web.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.tixter.exceptions.AuthenticationException;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.models.Users;
import com.revature.tixter.services.TicketService;
import com.revature.tixter.web.dtos.ErrorResponse;
import com.revature.tixter.web.dtos.NewTicketRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class TicketServlet extends HttpServlet {
    private final TicketService ticketService;
    private final ObjectMapper mapper;

    public TicketServlet(TicketService TicService, ObjectMapper mapper) {
        this.ticketService = TicService;
        this.mapper = mapper;
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

            NewTicketRequest newTicketRequest = mapper.readValue(request.getInputStream(), NewTicketRequest.class);
            newTicketRequest.setPublisher((Users) authUserAttribute);

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

        resp.setContentType("application/json");

        try {
            AppUser newUser = mapper.readValue(req.getInputStream(), AppUser.class);
            boolean wasRegistered = userService.registerNewUser(newUser);
            if (wasRegistered) {
                System.out.println("User successfully persisted!");
                resp.setStatus(201);
            } else {
                System.out.println("Could not persist user! Check logs.");
                resp.setStatus(500); // server error
            }
        } catch (JsonParseException e) {
            resp.setStatus(400); // client error; BAD REQUEST
            e.printStackTrace();
        }

    }
}
