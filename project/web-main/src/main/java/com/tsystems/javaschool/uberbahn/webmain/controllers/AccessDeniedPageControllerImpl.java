package com.tsystems.javaschool.uberbahn.webmain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessDeniedPageControllerImpl {

    @RequestMapping(path = "/accessDeniedPage", method = RequestMethod.GET)
    public String accessDenied() {
        return "accessDeniedPage";
    }
}
