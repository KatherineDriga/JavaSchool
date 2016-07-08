package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.StationService;
import com.tsystems.javaschool.uberbahn.transports.StationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AddStationControllerImpl {

    private final StationService stationService;

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
                                  @RequestParam(name = "timezone") int timezone) {

        StationInfo stationInfo = stationService.create(stationTitle, timezone);
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
