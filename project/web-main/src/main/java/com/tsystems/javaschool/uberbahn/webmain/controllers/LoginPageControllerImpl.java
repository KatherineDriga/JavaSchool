package com.tsystems.javaschool.uberbahn.webmain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginPageControllerImpl {

    @RequestMapping(path = "/loginPage", method = RequestMethod.GET)
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error) {

        if (error != null) {
            model.addAttribute("error", "Invalid login or password");
        }
        return "loginPage";
    }
}

