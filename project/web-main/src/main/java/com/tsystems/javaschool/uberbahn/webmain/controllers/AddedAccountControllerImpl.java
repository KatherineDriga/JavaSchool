package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AddedAccountControllerImpl {
    private final AccountService accountService;

    @Autowired
    public AddedAccountControllerImpl( AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/addedAccount", method = RequestMethod.GET)
    public String addedAccount(Model model, @RequestParam(name = "accountId") int id) {

        model.addAttribute("account", accountService.getById(id));

        return "addedAccount";
    }

}











