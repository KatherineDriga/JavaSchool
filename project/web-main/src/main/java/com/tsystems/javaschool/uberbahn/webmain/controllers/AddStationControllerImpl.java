package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddStationControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stationTitle = getRequiredParameter("stationTitle", req);


        StationInfo stationInfo = runTransaction((session -> {

            StationService service = new StationServiceImpl(session); // TODO: with DI
            return service.getStationInfo(stationTitle);
        }));

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        mapper.writeValue(out, stationInfo);
    }
}
