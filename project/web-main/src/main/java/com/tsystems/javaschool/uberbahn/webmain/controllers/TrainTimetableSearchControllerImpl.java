package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.StationService;

import com.tsystems.javaschool.uberbahn.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class TrainTimetableSearchControllerImpl {

    private final StationService stationService;
    private final TrainService trainService;

    @Autowired
    public TrainTimetableSearchControllerImpl(StationService stationService, TrainService trainService) {
        this.stationService = stationService;
        this.trainService = trainService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String showHomePage(Model model) {

        LocalDate sinceDate = LocalDate.now();
        LocalDate untilDate = sinceDate.plusDays(7);

        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("sinceDate", sinceDate);
        model.addAttribute("untilDate", untilDate);

        return "trainTimetableSearch";
    }

    @RequestMapping(path = "/listOfTrains", method = RequestMethod.GET)
    public String showListOfTrains(Model model, @RequestParam(name = "stationOfDeparture") int stationOfDepartureId,
                                   @RequestParam(name = "stationOfArrival") int stationOfArrivalId,
                                   @RequestParam(name = "since") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since,
                                   @RequestParam(name = "until") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime until) {

        model.addAttribute("trains", trainService.getAll(stationOfDepartureId, stationOfArrivalId, since, until));
        model.addAttribute("stationOfDepartureId", stationOfDepartureId);
        model.addAttribute("stationOfArrivalId", stationOfArrivalId);
        return "listOfTrains";
    }
}
