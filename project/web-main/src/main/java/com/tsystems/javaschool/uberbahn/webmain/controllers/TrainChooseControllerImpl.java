package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import com.tsystems.javaschool.uberbahn.webmain.transports.TrainInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;

public class TrainChooseControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int stationOfDeparture = getIntParameter("stationOfDeparture", req);
        int stationOfArrival = getIntParameter("stationOfArrival", req);
        LocalDateTime since = getDatetimeParameter("since", req);
        LocalDateTime until = getDatetimeParameter("until", req);

        /*int stationOfDeparture = 2;
        int stationOfArrival = 3;
        LocalDateTime since = LocalDateTime.of(2015,02,02,0,0);
        LocalDateTime until = LocalDateTime.of(2018,01,01,0,0);*/

        Collection<TrainInfo> trains = runTransaction((session -> {

            TrainService service = null; //new TrainServiceImpl(session); // TODO: with DI
            return service.getAll(stationOfDeparture, stationOfArrival, since, until);
        }));


        req.setAttribute("trains", trains);
        req.setAttribute("stationOfDepartureId", stationOfDeparture);
        req.setAttribute("stationOfArrivalId", stationOfArrival);

        render("trainChoose", req, resp);
    }
}
