package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.TrainTimetable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class TrainTimetableControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int stationOfDeparture = getIntParameter("stationOfDeparture", req);
        int stationOfArrival = getIntParameter("stationOfArrival", req);
        LocalDateTime since = getDatetimeParameter("since", req);
        LocalDateTime until = getDatetimeParameter("until", req);

        /*String stationOfDeparture = "St-Petersburg";
        String stationOfArrival = "Moscow";
        LocalDateTime since = LocalDateTime.of(2016,02,02,0,0);
        LocalDateTime until = LocalDateTime.of(2017,01,01,0,0);*/

        TrainTimetable timetable = runTransaction((session -> {

            TrainService service = null; //new TrainServiceImpl(session); // TODO: with DI
            return service.getTimetable(stationOfDeparture, stationOfArrival, since, until);
        }));

        req.setAttribute("timetable", timetable);
        req.setAttribute("since", since);
        req.setAttribute("until", until);

        render("trainTimetable", req, resp);
    }
}
