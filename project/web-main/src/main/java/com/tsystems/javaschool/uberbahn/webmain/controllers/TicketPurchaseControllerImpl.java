package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.uberbahn.webmain.services.TicketService;
import com.tsystems.javaschool.uberbahn.webmain.services.TicketServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;

public class TicketPurchaseControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int stationOfDepartureId = getIntParameter("stationOfDepartureId", req);
        int stationOfArrivalId = getIntParameter("stationOfArrivalId", req);
        int trainId = getIntParameter("trainId", req);
        String firstName = getRequiredParameter("firstName", req);
        String lastName = getRequiredParameter("lastName", req);
        LocalDate dateOfBirth = getDateParameter("dateOfBirth", req);
        int accountId = 1;

        TicketInfo ticketInfo = runTransaction((session -> {

            TicketService service = new TicketServiceImpl(session); // TODO: with DI
            return service.getTicketInfo(trainId, stationOfDepartureId, stationOfArrivalId, firstName, lastName, dateOfBirth, accountId);
        }));

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        //mapper.writeValue(out, ticketInfo.getId());
        mapper.writeValue(out, ticketInfo);

        /*Writer writer = resp.getWriter();
        writer.write("100500");
        writer.flush();*/
    }
}
