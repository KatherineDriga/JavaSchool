package com.tsystems.javaschool.uberbahn.webmain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class AddedTrainControllerImpl {

    @RequestMapping(path = "/addedTrain", method = RequestMethod.GET)
    public String addedTrainPage(Model model, @RequestParam(name = "trainId") int id) {

        model.addAttribute("trainId", id);

        return "addedTrain";
    }

}
