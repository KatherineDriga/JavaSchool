package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class FindTrainFormControllerImpl {
    private final RouteService routeService;

    @Autowired
    public FindTrainFormControllerImpl(RouteService routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(path = "/findTrainForm", method = RequestMethod.GET)
    public String findTrainForm(Model model) {

        model.addAttribute("routes", routeService.getAll());

        return "findTrainForm";
    }

}
