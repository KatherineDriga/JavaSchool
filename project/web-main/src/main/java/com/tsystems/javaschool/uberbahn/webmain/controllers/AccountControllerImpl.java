package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class AccountControllerImpl {

    private final AccountService accountService;
    private final Logger logger = LogManager.getLogger(TrainTimetableControllerImpl.class);


    @Autowired
    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/signUpForm", method = RequestMethod.GET)
    public String showSignUpForm() {
        return "signUpForm";
    }

    @ResponseBody
    @RequestMapping(path = "/signUpAccount", method = RequestMethod.POST, produces = "application/json")
    public AccountDetails signUpAccount (@RequestParam(name = "login") String login,
                                       @RequestParam(name = "email") String email,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "firstName") String firstName,
                                       @RequestParam(name = "lastName") String lastName,
                                       @RequestParam(name = "dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AccountDetails accountDetails = accountService.create(login, email, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, false);

        logger.info(String.format("Account %s is added", accountDetails.getId()));
        return accountDetails;
    }

    @RequestMapping(path = "/addedAccount", method = RequestMethod.GET)
    public String showAddedAccount(Model model, @RequestParam(name = "accountId") int id) {
        model.addAttribute("account", accountService.getById(id));
        return "addedAccount";
    }

}
