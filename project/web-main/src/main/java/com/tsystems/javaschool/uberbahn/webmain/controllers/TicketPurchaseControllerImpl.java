package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.TicketService;
import com.tsystems.javaschool.uberbahn.transports.TicketInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class TicketPurchaseControllerImpl {

    private final TicketService ticketService;

    @Autowired
    public TicketPurchaseControllerImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(path = "/ticketPurchase", method = RequestMethod.POST, produces = "application/json")
    public TicketInfo addTicket(@RequestParam(name = "stationOfDepartureId") int stationOfDepartureId,
                            @RequestParam(name = "stationOfArrivalId") int stationOfArrivalId,
                            @RequestParam(name = "trainId") int trainId,
                            @RequestParam(name = "firstName") String firstName,
                            @RequestParam(name = "lastName") String lastName,
                            @RequestParam(name = "dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                            @RequestParam(name = "accountLogin") String accountLogin) {


        TicketInfo ticketInfo = ticketService.create(trainId, stationOfDepartureId, stationOfArrivalId, firstName, lastName, dateOfBirth, accountLogin);
        return ticketInfo;
    }

}
