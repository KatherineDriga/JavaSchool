package com.tsystems.javaschool.uberbahn.webmain.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ErrorPagesControllerImpl {

    @RequestMapping(path = "/accessDeniedPage", method = RequestMethod.GET)
    public String accessDenied() {
        return "accessDeniedPage";
    }

    @RequestMapping(path = "/*", method = RequestMethod.GET)
    public String showErrorPage () {
        return "notExistingPage";
    }

}
