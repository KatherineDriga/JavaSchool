package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping ("/listOfTrains")
public class ListOfTrainsTableControllerImpl {

    private final TrainService trainService;

    @Autowired
    public ListOfTrainsTableControllerImpl(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(path = "/listOfTrains", method = RequestMethod.GET)
    public String showListOfTrains(Model model, @RequestParam(name = "stationOfDepartureId") int stationOfDepartureId,
                                   @RequestParam(name = "stationOfArrival") int stationOfArrival,
                                   @RequestParam(name = "since") LocalDateTime since,
                                   @RequestParam(name = "until") LocalDateTime until) {


        model.addAttribute("trains", trainService.getTrainInfo(stationOfDepartureId, stationOfArrival, since, until));

        return "listOfTrains";
    }
}
