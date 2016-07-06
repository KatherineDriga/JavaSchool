package com.tsystems.javaschool.uberbahn.webmain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AddedAccountControllerImpl {

    @RequestMapping(path = "/addedAccount", method = RequestMethod.GET)
    public String addedAccount(Model model, @RequestParam(name = "accountId") int id) {

        model.addAttribute("accountId", id);

        return "addedAccount";
    }

}











