package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import com.tsystems.javaschool.uberbahn.webmain.services.RouteServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AddStationsToRouteFormControllerImpl extends  BaseControllerImpl {

     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String routeTitle = getRequiredParameter("routeTitle", req);
        int numberOfStations = getIntParameter("numberOfStations", req);

        boolean existsRoute = runTransaction((session -> {

            RouteService service = new RouteServiceImpl(session); // TODO: with DI
            return service.existsRoute(routeTitle);
        }));

         if (existsRoute == true) {
             render("addRouteError", req, resp);
         }
        else {
             Collection<StationInfo> stations = runTransaction((session -> {

                 StationService service = new StationServiceImpl(session); // TODO: with DI
                 return service.getAll();
             }));

             req.setAttribute("stations", stations);
             req.setAttribute("routeTitle", routeTitle);
             req.setAttribute("numberOfStations", numberOfStations);

             render("addStationsToRouteForm", req, resp);
         }
    }
}
