package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import com.tsystems.javaschool.uberbahn.webmain.services.RouteServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RouteInfoControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int routeId = getIntParameter("routeId", req);

        RouteInfo route = runTransaction((session -> {

            RouteService service = new RouteServiceImpl(session); // TODO: with DI
            return service.getById(routeId);
        }));

        req.setAttribute("route", route);

        render("routeInfo", req, resp);
    }
}
