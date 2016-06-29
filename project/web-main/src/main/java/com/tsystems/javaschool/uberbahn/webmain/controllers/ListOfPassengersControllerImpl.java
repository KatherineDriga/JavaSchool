package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.TrainService;
import com.tsystems.javaschool.uberbahn.webmain.services.TrainServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.PassengerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class ListOfPassengersControllerImpl {
    private final TrainService trainService;

    @Autowired
    public ListOfPassengersControllerImpl(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(path = "/listOfPassengers", method = RequestMethod.GET)
    public String showListOfPassengers(Model model, @RequestParam(name = "trainId") int id) {

        model.addAttribute("passengerInfos", trainService.getPassengerInfo(id));
        model.addAttribute("trainId", id);

        return "listOfPassengers";
    }

}
