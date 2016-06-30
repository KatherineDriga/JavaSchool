package com.tsystems.javaschool.uberbahn.webmain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class AddedTrainControllerImpl {

    @RequestMapping(path = "/addedTrain", method = RequestMethod.GET)
    public String addedStationPage(Model model, @RequestParam(name = "trainId") int id) {

        model.addAttribute("trainId", id);

        return "addedTrain";
    }

}
