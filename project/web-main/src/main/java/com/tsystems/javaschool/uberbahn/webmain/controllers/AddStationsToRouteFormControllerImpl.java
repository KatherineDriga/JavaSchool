package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalTime;


@Controller
@RequestMapping("/")
public class AddStationsToRouteFormControllerImpl {
    private final RouteService routeService;
    private final StationService stationService;

    @Autowired
    public AddStationsToRouteFormControllerImpl(RouteService routeService, StationService stationService) {
        this.routeService = routeService;
        this.stationService = stationService;
    }

    @RequestMapping(path = "/addStationsToRouteForm", method = RequestMethod.GET)
    public String addStationsToRoute(Model model, @RequestParam(name = "routeTitle") String title,
                                     @RequestParam(name = "numberOfStations") int numberOfStations,
                                     @RequestParam(name = "timeOfDeparture") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeOfDeparture,
                                     @RequestParam(name = "pricePerMinute") @NumberFormat(pattern = "###.##") BigDecimal pricePerMinute) {

        boolean existsRoute = routeService.existsRoute(title);
        if (existsRoute == true) {
            return "addRouteError";
        } else {
            model.addAttribute("stations", stationService.getAll());
            model.addAttribute("routeTitle", title);
            model.addAttribute("numberOfStations", numberOfStations);
            model.addAttribute("timeOfDeparture", timeOfDeparture);
            model.addAttribute("pricePerMinute", pricePerMinute);

            return "addStationsToRouteForm";
        }
    }

}
