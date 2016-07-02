package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.webmain.services.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping ("/")
public class TrainTimetableSearchFormControllerImpl {

    private final StationService stationService;

    @Autowired
    public TrainTimetableSearchFormControllerImpl(StationService stationService) {
        this.stationService = stationService;
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
}
