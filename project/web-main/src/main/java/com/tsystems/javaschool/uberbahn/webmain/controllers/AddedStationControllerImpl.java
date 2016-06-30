package com.tsystems.javaschool.uberbahn.webmain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class AddedStationControllerImpl {

    @RequestMapping(path = "/addedStation", method = RequestMethod.GET)
    public String addedStationPage(Model model, @RequestParam(name = "stationId") int id,
                                   @RequestParam(name = "title") String title) {

        model.addAttribute("stationId", id);
        model.addAttribute("title", title);

        return "addedStation";
    }
}
