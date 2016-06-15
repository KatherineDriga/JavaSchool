package com.tsystems.javaschool.uberbahn.webmain.controllers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class TicketPurchaseControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int stationOfDeparture = getIntParameter("stationOfDepartureId", req);
        int stationOfArrival = getIntParameter("stationOfArrivalId", req);
        int trainId = getIntParameter("trainId", req);
        String firstName = getRequiredParameter("firstName", req);
        String lastName = getRequiredParameter("lastName", req);
        String dateOfBirth = getRequiredParameter("dateOfBirth", req);


/*
        Collection<TrainInfo> trains = runTransaction((session -> {

            TrainService service = new TrainServiceImpl(session); // TODO: with DI
            return service.getTrainInfo(stationOfDeparture, stationOfArrival, since, until);
        }));
*/

        Writer writer = resp.getWriter();
        writer.write("100500");
        writer.flush();
    }
}
