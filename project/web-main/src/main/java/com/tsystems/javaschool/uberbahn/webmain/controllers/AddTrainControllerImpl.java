package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.RouteService;
import com.tsystems.javaschool.uberbahn.services.TrainService;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class AddTrainControllerImpl {

    private final TrainService trainService;
    private final RouteService routeService;

    @Autowired
    public AddTrainControllerImpl(TrainService trainService, RouteService routeService) {
        this.trainService = trainService;
        this.routeService = routeService;
    }

    @RequestMapping(path = "/addTrainForm", method = RequestMethod.GET)
    public String showAddTrainForm(Model model) {

        model.addAttribute("routes", routeService.getAll());

        return "addTrainForm";
    }

    @ResponseBody
    @RequestMapping(path = "/addTrain", method = RequestMethod.POST, produces = "application/json")
    public TrainInfo addTrain(@RequestParam(name = "routeId") int routeId,
                              @RequestParam(name = "dateOfDeparture") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfDeparture,
                              @RequestParam(name = "numberOfSeats") int numberOfSeats,
                              @RequestParam(name = "priceCoefficient") double priceCoefficient) {

        TrainInfo trainInfo = trainService.create(routeId, dateOfDeparture, numberOfSeats, priceCoefficient);
        return trainInfo;
    }

    @RequestMapping(path = "/addedTrain", method = RequestMethod.GET)
    public String showAddedTrainPage(Model model, @RequestParam(name = "trainId") int id) {
        model.addAttribute("trainId", id);
        return "addedTrain";
    }
}
