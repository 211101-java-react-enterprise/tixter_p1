package com.revature.tixter.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.tixter.daos.UserDAO;
import com.revature.tixter.services.UserService;
import com.revature.tixter.web.servlets.RegisterServlet;


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

        RegisterServlet userServlet = new RegisterServlet(userService, objectMapper);

        ServletContext context=sce.getServletContext();
        context.addServlet("RegisterServlet",userServlet).addMapping("/register");

        System.out.println("Ready To Go");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
