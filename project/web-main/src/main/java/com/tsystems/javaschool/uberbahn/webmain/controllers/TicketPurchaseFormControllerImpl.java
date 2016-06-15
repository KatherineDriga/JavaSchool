package com.tsystems.javaschool.uberbahn.webmain.controllers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TicketPurchaseFormControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int stationOfDeparture = getIntParameter("stationOfDeparture", req);
        int stationOfArrival = getIntParameter("stationOfArrival", req);
        int trainId = getIntParameter("trainId", req);


        req.setAttribute("stationOfDepartureId", stationOfDeparture);
        req.setAttribute("stationOfArrivalId", stationOfArrival);
        req.setAttribute("trainId", trainId);

        render("ticketPurchaseForm", req, resp);
    }
}
