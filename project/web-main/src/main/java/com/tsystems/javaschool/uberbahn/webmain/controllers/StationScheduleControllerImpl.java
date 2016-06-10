package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.temp.services.ModelService;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;


public class StationScheduleControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String station = null;
        int stationId = 0;
        LocalDate sinceDate = null;
        LocalTime sinceTime = null;
        LocalDate untilDate = null;
        LocalTime untilTime = null;

        Collection<StationScheduleEvent> events = runTransaction((session -> {

            StationService service = new StationServiceImpl(session); // TODO: with DI
            return service.getScheduleEvents(stationId, sinceDate, sinceTime, untilDate, untilTime);
        }));

        req.setAttribute("trainScheduleEvents", events);
        req.setAttribute("station", station);

        render("stationSchedule", req, resp);
    }
}
