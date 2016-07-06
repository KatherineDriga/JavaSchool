package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class SignUpAccountControllerImpl {

    private final AccountService accountService;

    @Autowired
    public SignUpAccountControllerImpl (AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/signUpAccount", method = RequestMethod.POST, produces = "application/json")
    public AccountDetails signUpAccount (@RequestParam(name = "login") String login,
                                       @RequestParam(name = "email") String email,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "firstName") String firstName,
                                       @RequestParam(name = "lastName") String lastName,
                                       @RequestParam(name = "dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                       @RequestParam(name = "employee") boolean employee) {

         AccountDetails accountDetails = accountService.create(login, email, password, firstName, lastName, dateOfBirth, employee);

        return accountDetails;
    }

}
