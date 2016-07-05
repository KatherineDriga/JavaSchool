package com.tsystems.javaschool.uberbahn.webmain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class AddStationFormControllerImpl {

    @RequestMapping(path = "/addStationForm", method = RequestMethod.GET)
    public ModelAndView addStationForm() {
        ModelAndView model = new ModelAndView();
        model.setViewName("addStationForm");
        return model;
    }

}

