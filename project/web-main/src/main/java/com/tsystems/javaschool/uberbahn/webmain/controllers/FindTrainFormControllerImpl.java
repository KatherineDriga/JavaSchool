package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.RouteService;
import com.tsystems.javaschool.uberbahn.webmain.services.RouteServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

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
