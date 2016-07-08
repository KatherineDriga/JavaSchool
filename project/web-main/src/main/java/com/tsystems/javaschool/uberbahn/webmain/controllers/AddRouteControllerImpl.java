package com.tsystems.javaschool.uberbahn.webmain.controllers;



import com.tsystems.javaschool.uberbahn.services.RouteService;
import com.tsystems.javaschool.uberbahn.services.StationService;
import com.tsystems.javaschool.uberbahn.transports.RouteInfo;
import com.tsystems.javaschool.uberbahn.webmain.exception.CustomGenericException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AddRouteControllerImpl {

    private final RouteService routeService;
    private final StationService stationService;
    private final Logger logger = Logger.getLogger(TrainTimetableSearchControllerImpl.class);

    @Autowired
    public AddRouteControllerImpl(RouteService routeService, StationService stationService) {
        this.routeService = routeService;
        this.stationService = stationService;
    }

    @RequestMapping(path = "/addRouteForm", method = RequestMethod.GET)
    public String addRouteForm() {
        return "addRouteForm";
    }


    @RequestMapping(path = "/addStationsToRouteForm", method = RequestMethod.GET)
    public String addStationsToRoute(Model model, @RequestParam(name = "routeTitle") String title,
                                     @RequestParam(name = "numberOfStations") int numberOfStations,
                                     @RequestParam(name = "timeOfDeparture") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeOfDeparture,
                                     @RequestParam(name = "pricePerMinute") @NumberFormat(pattern = "###.##") BigDecimal pricePerMinute)
            throws Exception {

        boolean existsRoute = routeService.existsRoute(title);
        if (existsRoute == true) {
            throw new CustomGenericException(String.format("Route %s already exists", title));
        }
            model.addAttribute("stations", stationService.getAll());
            model.addAttribute("routeTitle", title);
            model.addAttribute("numberOfStations", numberOfStations);
            model.addAttribute("timeOfDeparture", timeOfDeparture);
            model.addAttribute("pricePerMinute", pricePerMinute);
            return "addStationsToRouteForm";
    }

    @ResponseBody
    @RequestMapping(path = "/addRoute", method = RequestMethod.POST, produces = "application/json")
    public RouteInfo addRoute(@RequestParam(name = "title") String title,
                              @RequestParam(name = "timeOfDeparture") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeOfDeparture,
                              @RequestParam(name = "stationIds") String stationIds,
                              @RequestParam(name = "minutesSinceDepartures") String minutesSinceDepartures,
                              @RequestParam(name = "pricePerMinute") @NumberFormat(pattern = "###.##") BigDecimal pricePerMinute)
    throws PersistenceException {

        List<Integer> ids = Arrays.asList(stationIds.split(";"))
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        List<Integer> minutes =Arrays.asList(minutesSinceDepartures.split((";")))
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());


        RouteInfo routeInfo = null;
        try {
            routeInfo = routeService.create(title, timeOfDeparture,
                    ids, minutes, pricePerMinute);
        } catch (PersistenceException ex) {
            throw new PersistenceException("Database writing error", ex);
        }
        logger.info(String.format("Route %s is added", routeInfo.getId()));
        return routeInfo;
    }

    @RequestMapping(path = "/routeInfo", method = RequestMethod.GET)
    public String showRouteInfo(Model model, @RequestParam(name = "routeId") int routeId) {

        model.addAttribute("route", routeService.getById(routeId));
        return "routeInfo";
    }

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomException(CustomGenericException ex) {
        logger.warn("WARN", ex);
        ModelAndView model = new ModelAndView("addStationsToRouteForm");
        model.addObject("exception", ex.getErrorMsg());
        return model;
    }

}
