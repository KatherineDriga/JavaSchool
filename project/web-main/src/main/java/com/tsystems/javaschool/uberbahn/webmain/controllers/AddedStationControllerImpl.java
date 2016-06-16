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

public class AddedStationControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int stationId = getIntParameter("stationId", req);
        String title = getRequiredParameter("title", req);
        String message = getRequiredParameter("message", req);


        req.setAttribute("stationId", stationId);
        req.setAttribute("title", title);
        req.setAttribute("message", message);

        render("addedStation", req, resp);

    }
}
