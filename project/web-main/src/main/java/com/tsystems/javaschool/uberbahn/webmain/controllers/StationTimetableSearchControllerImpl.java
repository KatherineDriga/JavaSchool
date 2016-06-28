package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class StationTimetableSearchControllerImpl {
    private final StationService stationService;

    @Autowired
    public StationTimetableSearchControllerImpl(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(path = "/stationTimetableSearch", method = RequestMethod.GET)
    public String showStations(Model model) {

        LocalDate sinceDate = LocalDate.now();
        LocalDate untilDate = sinceDate.plusDays(7);

        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("sinceDate", sinceDate);
        model.addAttribute("untilDate", untilDate);

        return "stationTimetableSearch";
    }

}
