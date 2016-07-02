package com.tsystems.javaschool.uberbahn.webmain.controllers;



import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class AddRouteControllerImpl {

    private final RouteService routeService;

    @Autowired
    public AddRouteControllerImpl(RouteService routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(path = "/addRoute", method = RequestMethod.POST, produces = "application/json")
    public RouteInfo addRoute(@RequestParam(name = "title") String title,
                              @RequestParam(name = "timeOfDeparture") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeOfDeparture,
                              @RequestParam(name = "stationIds") String stationIds,
                              @RequestParam(name = "minutesSinceDepartures") String minutesSinceDepartures,
                              @RequestParam(name = "pricePerMinute") @NumberFormat(pattern = "###.##") BigDecimal pricePerMinute) {

        List<Integer> ids = Arrays.asList(stationIds.split(";"))
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        List<Integer> minutes =Arrays.asList(minutesSinceDepartures.split((";")))
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        RouteInfo routeInfo = routeService.create(title, timeOfDeparture,
                ids, minutes, pricePerMinute);
        return routeInfo;
    }

}
