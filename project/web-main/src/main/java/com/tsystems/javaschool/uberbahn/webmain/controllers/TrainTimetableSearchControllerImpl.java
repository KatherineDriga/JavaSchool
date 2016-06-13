package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.webmain.transports.TrainInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class TrainTimetableSearchControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<StationInfo> stations = runTransaction((session -> {

            StationService service = new StationServiceImpl(session); // TODO: with DI
            return service.getAll();
        }));

        req.setAttribute("stations", stations);

        render("trainTimetableSearch", req, resp);
    }
}
