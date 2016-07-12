package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import com.tsystems.javaschool.uberbahn.webmain.errors.BusinessLogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
                                       @RequestParam(name = "dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                       @RequestParam(name = "employee") boolean employee) {

        if (accountService.existsLogin(login)) {
            throw new BusinessLogicException(String.format("Login %s already exists", login));
        }
        if (accountService.existsEmail(email)) {
            throw new BusinessLogicException(String.format("Email %s already exists", email));
        }

        AccountDetails accountDetails = accountService.create(login, email, password, firstName, lastName, dateOfBirth, employee);

        logger.info(String.format("Account %s is added", accountDetails.getId()));
        return accountDetails;
    }

    @RequestMapping(path = "/addedAccount", method = RequestMethod.GET)
    public String showAddedAccount(Model model, @RequestParam(name = "accountId") int id) {
        model.addAttribute("account", accountService.getById(id));
        return "addedAccount";
    }

}
