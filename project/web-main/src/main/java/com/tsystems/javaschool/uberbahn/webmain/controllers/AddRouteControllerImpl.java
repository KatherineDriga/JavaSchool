package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import com.tsystems.javaschool.uberbahn.webmain.services.RouteServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AddRouteControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = getRequiredParameter("title", req);
        LocalTime timeOfDeparture = getTimeParameter("timeOfDeparture", req);
        List<Integer> stationIds = getIntArrayParameter("stationIds", req);
        List<Integer> minutesSinceDepartures = getIntArrayParameter("minutesSinceDepartures", req);


        RouteInfo route = runTransaction((session -> {

            RouteService service = null;//new RouteServiceImpl(session); // TODO: with DI
            return service.create(title,timeOfDeparture,stationIds,minutesSinceDepartures);
        }));

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        mapper.writeValue(out, route);

    }
}
