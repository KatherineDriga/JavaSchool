package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class StationTimetableControllerImpl {
    private final StationService stationService;

    @Autowired
    public StationTimetableControllerImpl(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(path = "/stationTimetable", method = RequestMethod.GET)
    public String showStationTimetable(Model model, @RequestParam(name = "stationId") int stationId,
                                       @RequestParam(name = "since") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since,
                                       @RequestParam(name = "until") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime until) {

        model.addAttribute("timetable", stationService.getTimetable(stationId, since, until));

        return "stationTimetable";
    }

}
