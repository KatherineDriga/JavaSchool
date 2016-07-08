package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.entities.Station;
import com.tsystems.javaschool.uberbahn.services.StationService;
import com.tsystems.javaschool.uberbahn.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.webmain.exception.CustomGenericException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;


@Controller
public class AddStationControllerImpl {

    private final StationService stationService;
    private final Logger logger = Logger.getLogger(TrainTimetableSearchControllerImpl.class);


    @Autowired
    public AddStationControllerImpl(StationService stationService) {
        this.stationService = stationService;
    }


    @RequestMapping(path = "/addStationForm", method = RequestMethod.GET)
    public String showAddStationForm() {
        return "addStationForm";
    }

    @ResponseBody
    @RequestMapping(path = "/addStation", method = RequestMethod.POST, produces = "application/json")
    public StationInfo addStation(@RequestParam(name = "stationTitle") String stationTitle,
                                  @RequestParam(name = "timezone") int timezone)
            throws Exception{

        boolean existsStation = stationService.existsStation(stationTitle);
        if (existsStation == true) {
            throw new PersistenceException(String.format("Station %s already exists", stationTitle));
        }
        StationInfo stationInfo = null;
        try {
            stationInfo = stationService.create(stationTitle, timezone);
        } catch (PersistenceException ex) {
            throw new PersistenceException("Database writing error", ex);
        }
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
