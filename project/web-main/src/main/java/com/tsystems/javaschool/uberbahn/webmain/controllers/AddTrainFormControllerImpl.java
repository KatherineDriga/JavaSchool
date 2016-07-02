package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class AddTrainFormControllerImpl {

    private final RouteService routeService;

    @Autowired
    public AddTrainFormControllerImpl(RouteService routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(path = "/addTrainForm", method = RequestMethod.GET)
    public String addTrainForm(Model model) {

        model.addAttribute("routes", routeService.getAll());

        return "addTrainForm";
    }

}
