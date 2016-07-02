package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class TableOfTrainsControllerImpl {

    private final TrainService trainService;

    @Autowired
    public TableOfTrainsControllerImpl(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(path = "/tableOfTrains", method = RequestMethod.GET)
    public String addTrainForm(Model model, @RequestParam(name = "routeId") int id) {

        model.addAttribute("trainInfo", trainService.getTrainInfos(id));

        return "tableOfTrains";
    }

}