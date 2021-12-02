package com.revature.tixter.web.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.tixter.models.Users;
import com.revature.tixter.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    private UserService userService;
    private ObjectMapper objectMapper;

    public RegisterServlet(UserService userService, ObjectMapper objectMapper) {
        this.userService=userService;
        this.objectMapper=objectMapper;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        PrintWriter respWriter=resp.getWriter();
        resp.setContentType("application/json");


        try {
            Users newUser = objectMapper.readValue(req.getInputStream(), Users.class);
            //System.out.println(newUser.getEmail()+": "+newUser.getFirstname());
            boolean wasRegistered = userService.register(newUser);
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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
