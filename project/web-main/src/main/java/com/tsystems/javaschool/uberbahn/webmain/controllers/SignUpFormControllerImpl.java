package com.tsystems.javaschool.uberbahn.webmain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SignUpFormControllerImpl {

    @RequestMapping(path = "/signUpForm", method = RequestMethod.GET)
    public String showSignUpForm() {

        return "signUpForm";
    }
}