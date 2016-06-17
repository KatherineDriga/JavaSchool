package com.tsystems.javaschool.uberbahn.webmain.controllers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRouteControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] stations = req.getParameterValues("stations");
        String[] minutesSinceDepartures = req.getParameterValues("minutesSinceDepartures");


       /*TicketInfo ticketInfo = runTransaction((session -> {

            TicketService service = new TicketServiceImpl(session); // TODO: with DI
            return service.getTicketInfo(trainId, stationOfDepartureId, stationOfArrivalId, firstName, lastName, dateOfBirth, accountId);
        }));

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        mapper.writeValue(out, ticketInfo);*/

    }
}
