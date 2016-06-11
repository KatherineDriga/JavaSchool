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

        int stationId = getIntParameter("stationId", req);
        LocalDateTime since = getDatetimeParameter("since", req);
        LocalDateTime until = getDatetimeParameter("until", req);

        StationTimetable timetable = runTransaction((session -> {

            StationService service = new StationServiceImpl(session); // TODO: with DI
            return service.getTimetable(stationId, since, until);
        }));

        req.setAttribute("timetable", timetable);

        render("stationTimetable", req, resp);
    }
}
