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
@RequestMapping ("/")
public class ListOfTrainsTableControllerImpl {

    private final TrainService trainService;

    @Autowired
    public ListOfTrainsTableControllerImpl(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(path = "/listOfTrains", method = RequestMethod.GET)
    public String showListOfTrains(Model model, @RequestParam(name = "stationOfDeparture") int stationOfDepartureId,
                                   @RequestParam(name = "stationOfArrival") int stationOfArrivalId/*,
                                   @RequestParam(name = "since") LocalDateTime since,
                                   @RequestParam(name = "until") LocalDateTime until*/) {

        LocalDateTime since = LocalDateTime.of(2015,01,01,0,0);
        LocalDateTime until = LocalDateTime.of(2017,01,01,0,0);
        model.addAttribute("trains", trainService.getTrainInfo(stationOfDepartureId, stationOfArrivalId, since, until));
        model.addAttribute("stationOfDepartureId", stationOfDepartureId);
        model.addAttribute("stationOfArrivalId", stationOfArrivalId);
        return "listOfTrains";
    }
}
