package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.RouteService;
import com.tsystems.javaschool.uberbahn.services.TrainService;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.time.LocalDate;

@Controller
public class AddTrainControllerImpl {

    private final TrainService trainService;
    private final RouteService routeService;
    private final Logger logger = LogManager.getLogger(TrainTimetableSearchControllerImpl.class);


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

        boolean existsTrain = trainService.existsTrain(routeId, dateOfDeparture);
        if (existsTrain == true) {
            throw new PersistenceException(String.format("Train %s already exists", dateOfDeparture));
        }
        TrainInfo trainInfo = null;
        try {
           trainInfo = trainService.create(routeId, dateOfDeparture, numberOfSeats, priceCoefficient);
        } catch (PersistenceException ex) {
            throw new PersistenceException("Database writing error", ex);
        }

        logger.info(String.format("Train %s is added", trainInfo.getTrainId()));
        return trainInfo;
    }

    @RequestMapping(path = "/addedTrain", method = RequestMethod.GET)
    public String showAddedTrainPage(Model model, @RequestParam(name = "trainId") int id) {
        model.addAttribute("trainId", id);
        return "addedTrain";
    }
}
