package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TicketService;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class PurchasedTicketControllerImpl {

    private final TicketService ticketService;

    @Autowired
    public PurchasedTicketControllerImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(path = "/purchasedTicket", method = RequestMethod.GET)
    public TicketInfo showTicketInfo(@RequestParam(name = "ticketId") int id) {

        TicketInfo ticketInfo = ticketService.getTicketInfo(id);
        return ticketInfo;
    }

}
