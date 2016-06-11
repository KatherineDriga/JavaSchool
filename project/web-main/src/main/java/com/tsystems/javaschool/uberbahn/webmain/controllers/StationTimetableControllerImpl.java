package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationTimetable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class StationTimetableControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String station = "St-Petersburg";
        int stationId = 2;
        LocalDateTime sinceDateTime = LocalDateTime.of(2016,05,30,10,0);
        LocalDateTime untilDateTime = LocalDateTime.of(2016,8,8,10,0);



        StationTimetable timetable = runTransaction((session -> {

            StationService service = new StationServiceImpl(session); // TODO: with DI
            return service.getTimetable(stationId, sinceDateTime, untilDateTime);
        }));

        req.setAttribute("timetable", timetable);

        render("stationTimetable", req, resp);
    }
}
