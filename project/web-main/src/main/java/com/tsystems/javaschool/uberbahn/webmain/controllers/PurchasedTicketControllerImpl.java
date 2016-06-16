package com.tsystems.javaschool.uberbahn.webmain.controllers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchasedTicketControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int ticketId = getIntParameter("ticketId", req);
        String message = getRequiredParameter("message", req);


        req.setAttribute("ticketId", ticketId);
        req.setAttribute("message", message);

        render("purchasedTicket", req, resp);
    }
}
