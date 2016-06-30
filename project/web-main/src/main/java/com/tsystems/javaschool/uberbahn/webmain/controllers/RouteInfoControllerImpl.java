package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class RouteInfoControllerImpl {

    private final RouteService routeService;

    @Autowired
    public RouteInfoControllerImpl(RouteService routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(path = "/routeInfo", method = RequestMethod.GET)
    public String showRouteInfo(Model model, @RequestParam(name = "routeId") int routeId) {

        model.addAttribute("route", routeService.getById(routeId));
        return "routeInfo";
    }

}
