package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.RouteService;
import com.tsystems.javaschool.uberbahn.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FindTrainAndPassengersControllerImpl {

    private final RouteService routeService;
    private final TrainService trainService;

    @Autowired
    public FindTrainAndPassengersControllerImpl(RouteService routeService, TrainService trainService) {
        this.routeService = routeService;
        this.trainService = trainService;
    }

    @RequestMapping(path = "/findTrainForm", method = RequestMethod.GET)
    public String showFindTrainForm(Model model) {
        model.addAttribute("routes", routeService.getAll());
        return "findTrainForm";
    }

    @RequestMapping(path = "/tableOfTrains", method = RequestMethod.GET)
    public String showTableOfTrains(Model model, @RequestParam(name = "routeId") int id) {
        model.addAttribute("trainInfo", trainService.getTrainInfos(id));
        return "tableOfTrains";
    }

    @RequestMapping(path = "/listOfPassengers", method = RequestMethod.GET)
    public String showListOfPassengers(Model model, @RequestParam(name = "trainId") int id) {

        model.addAttribute("passengerInfos", trainService.getPassengerInfo(id));
        model.addAttribute("trainId", id);
        return "listOfPassengers";
    }

}
