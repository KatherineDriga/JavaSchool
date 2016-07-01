package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TicketService;
import com.tsystems.javaschool.uberbahn.webmain.services.TicketServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class PurchasedTicketControllerImpl {

    private final TicketService ticketService;

    @Autowired
    public PurchasedTicketControllerImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(path = "/purchasedTicket", method = RequestMethod.GET)
    public TicketInfo showTicketInfo(Model model, @RequestParam(name = "ticketId") int id) {

        TicketInfo ticketInfo = ticketService.getTicketInfoByTicketId(id);
        return ticketInfo;
    }

}
