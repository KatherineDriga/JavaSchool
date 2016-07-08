package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.time.LocalDate;

@Controller
public class SignUpAccountControllerImpl {

    private final AccountService accountService;

    @Autowired
    public SignUpAccountControllerImpl (AccountService accountService) {
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
                                       @RequestParam(name = "dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                       @RequestParam(name = "employee") boolean employee) {

        if (accountService.existsLogin(login)) {
            throw new PersistenceException(String.format("Login %s already exists", login));
        }
        if (accountService.existsEmail(email)) {
            throw new PersistenceException(String.format("Email %s already exists", email));
        }

        return accountService.create(login, email, password, firstName, lastName, dateOfBirth, employee);

    }

    @RequestMapping(path = "/addedAccount", method = RequestMethod.GET)
    public String showAddedAccount(Model model, @RequestParam(name = "accountId") int id) {
        model.addAttribute("account", accountService.getById(id));
        return "addedAccount";
    }

}
