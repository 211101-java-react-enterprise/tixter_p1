package com.revature.tixter.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.tixter.daos.TicketDAO;
import com.revature.tixter.daos.UserDAO;
import com.revature.tixter.services.TicketService;
import com.revature.tixter.services.UserService;
import com.revature.tixter.web.servlets.LoginServlet;
import com.revature.tixter.web.servlets.RegisterServlet;
import com.revature.tixter.web.servlets.TicketServlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Welcome to Tixter");

        ObjectMapper objectMapper = new ObjectMapper();

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);

        TicketDAO ticketDAO = new TicketDAO();
        TicketService ticketService = new TicketService(ticketDAO) ;

        RegisterServlet userServlet = new RegisterServlet(userService, objectMapper);
        LoginServlet loginServlet = new LoginServlet(userService, objectMapper);
        TicketServlet ticketServlet = new TicketServlet(ticketService,objectMapper);

        ServletContext context=sce.getServletContext();
        context.addServlet("RegisterServlet",userServlet).addMapping("/register");
        context.addServlet("LoginServlet",loginServlet).addMapping("/login");
        context.addServlet("TicketServlet", ticketServlet).addMapping("/tickets");

        System.out.println("Ready To Go");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
