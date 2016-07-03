package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.TrainService;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class AddTrainControllerImpl {

    private final TrainService trainService;

    @Autowired
    public AddTrainControllerImpl(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(path = "/addTrain", method = RequestMethod.POST, produces = "application/json")
    public TrainInfo addTrain(@RequestParam(name = "routeId") int routeId,
                              @RequestParam(name = "dateOfDeparture") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfDeparture,
                              @RequestParam(name = "numberOfSeats") int numberOfSeats,
                              @RequestParam(name = "priceCoefficient") double priceCoefficient) {

        TrainInfo trainInfo = trainService.create(routeId, dateOfDeparture, numberOfSeats, priceCoefficient);
        return trainInfo;
    }
}
