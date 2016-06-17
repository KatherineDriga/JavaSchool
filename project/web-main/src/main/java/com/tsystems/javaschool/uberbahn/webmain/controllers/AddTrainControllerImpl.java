package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.AddTrainInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class AddTrainControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int routeId = getIntParameter("routeId", req);
        LocalDate dateOfDeparture = getDateParameter("dateOfDeparture", req);
        int numberOfSeats = getIntParameter("numberOfSeats", req);

        AddTrainInfo addTrainInfo = runTransaction((session -> {

            TrainService service = new TrainServiceImpl(session); // TODO: with DI
            return service.getAddTrainInfo(routeId, dateOfDeparture, numberOfSeats);
        }));

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        mapper.writeValue(out, addTrainInfo);


    }
}
