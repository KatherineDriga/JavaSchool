package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TicketService;
import com.tsystems.javaschool.uberbahn.webmain.services.TicketServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class PurchasedTicketControllerImpl extends BaseControllerImpl {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int ticketId = getIntParameter("ticketId", req);

        TicketInfo ticket = runTransaction((session -> {

            TicketService service = null;//new TicketServiceImpl(session); // TODO: with DI
            return service.getTicketInfoByTicketId(ticketId);
        }));



        req.setAttribute("ticket", ticket);


        render("purchasedTicket", req, resp);
    }
}
