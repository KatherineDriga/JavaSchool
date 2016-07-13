package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.StationService;
import com.tsystems.javaschool.uberbahn.transports.StationInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class StationControllerImpl {

    private final StationService stationService;
    private final Logger logger = LogManager.getLogger(TrainTimetableControllerImpl.class);


    @Autowired
    public StationControllerImpl(StationService stationService) {
        this.stationService = stationService;
    }


    @RequestMapping(path = "/addStationForm", method = RequestMethod.GET)
    public String showAddStationForm() {
        return "addStationForm";
    }

    @ResponseBody
    @RequestMapping(path = "/addStation", method = RequestMethod.POST, produces = "application/json")
    public StationInfo addStation(@RequestParam(name = "stationTitle") String stationTitle,
                                  @RequestParam(name = "timezone") int timezone) {


        StationInfo stationInfo = stationService.create(stationTitle, timezone);
        logger.info(String.format("Station %s is added", stationInfo.getId()));
        return stationInfo;
    }


    @RequestMapping(path = "/addedStation", method = RequestMethod.GET)
    public String showAddedStationPage(Model model, @RequestParam(name = "stationId") int id,
                                   @RequestParam(name = "title") String title) {
        model.addAttribute("stationId", id);
        model.addAttribute("title", title);
        return "addedStation";
    }


}
