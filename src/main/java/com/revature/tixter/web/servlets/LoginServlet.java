package com.revature.tixter.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.tixter.exceptions.AuthenticationException;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.models.Users;
import com.revature.tixter.services.UserService;
import com.revature.tixter.web.dtos.Credentials;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final UserService userService;
    private final ObjectMapper mapper;

    public LoginServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    // login
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Credentials cred = mapper.readValue(req.getInputStream(), Credentials.class);
            Users authUser = userService.authenticateUser(cred.getEmail(), cred.getPassword());

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("authUser", authUser); //sets cookie

            resp.setStatus(200);
        } catch (InvalidRequestException | UnrecognizedPropertyException e) {
            resp.setStatus(400);
        } catch (AuthenticationException e) {
            resp.setStatus(401);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }

    // logout
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate(); // invalidates the session associated with this request (logging the user out)
    }

}