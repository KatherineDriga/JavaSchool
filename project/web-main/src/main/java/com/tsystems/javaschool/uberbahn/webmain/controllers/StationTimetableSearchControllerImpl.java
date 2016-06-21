package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

public class StationTimetableSearchControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<StationInfo> stations = runTransaction((session -> {

            StationService service = new StationServiceImpl(session); // TODO: with DI
            return service.getAll();
        }));
        LocalDate sinceDate = LocalDate.now();
        LocalDate untilDate = sinceDate.plusDays(7);

        req.setAttribute("sinceDate", sinceDate);
        req.setAttribute("untilDate", untilDate);

        req.setAttribute("stations", stations);

        render("stationTimetableSearch", req, resp);
    }
}
