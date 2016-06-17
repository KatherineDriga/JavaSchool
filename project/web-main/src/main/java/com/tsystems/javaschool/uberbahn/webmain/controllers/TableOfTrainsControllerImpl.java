package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.FindTrainInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class TableOfTrainsControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int routeId = getIntParameter("routeId", req);

        Collection<FindTrainInfo> findTrainInfos = runTransaction((session -> {

            TrainService service = new TrainServiceImpl(session); // TODO: with DI
            return service.getFindTrainInfo(routeId);
        }));


        req.setAttribute("findTrainInfo", findTrainInfos);

        render("tableOfTrains", req, resp);
    }
}