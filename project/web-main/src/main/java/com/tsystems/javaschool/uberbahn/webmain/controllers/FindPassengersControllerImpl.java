package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.PassengerInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class FindPassengersControllerImpl extends  BaseControllerImpl{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int trainId = getIntParameter("trainId", req);

        Collection<PassengerInfo> passengerInfos = runTransaction((session -> {

            TrainService service = null; // new TrainServiceImpl(session); // TODO: with DI
            return service.getPassengerInfo(trainId);
        }));


        req.setAttribute("passengerInfos", passengerInfos);
        req.setAttribute("trainId", trainId);

        render("listOfPassengers", req, resp);
    }
}
