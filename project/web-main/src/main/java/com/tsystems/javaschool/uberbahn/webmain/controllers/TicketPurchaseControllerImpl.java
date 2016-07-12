package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.services.TicketService;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import com.tsystems.javaschool.uberbahn.transports.TicketInfo;
import com.tsystems.javaschool.uberbahn.webmain.errors.BusinessLogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Controller
public class TicketPurchaseControllerImpl {

    private final TicketService ticketService;
    private final AccountService accountService;
    private final UserDetailsService userDetailsService;
    private final Logger logger = LogManager.getLogger(TrainTimetableSearchControllerImpl.class);

    @Autowired
    public TicketPurchaseControllerImpl(TicketService ticketService, AccountService accountService, UserDetailsService userDetailsService) {
        this.ticketService = ticketService;
        this.accountService = accountService;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(path = "/ticketPurchaseForm", method = RequestMethod.GET)
    public String showTicketPurchaseForm(Model model, @RequestParam(name = "stationOfDeparture") int stationOfDepartureId,
                                     @RequestParam(name = "stationOfArrival") int stationOfArrivalId,
                                     @RequestParam(name = "trainId") int trainId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        model.addAttribute("stationOfDepartureId", stationOfDepartureId);
        model.addAttribute("stationOfArrivalId", stationOfArrivalId);
        model.addAttribute("trainId", trainId);
        AccountDetails accountDetails = accountService.getByLogin(userName);
        if (!accountDetails.isEmployee()){
            model.addAttribute("account", accountService.getByLogin(userName));
        }
        return "ticketPurchaseForm";
    }

    @ResponseBody
    @RequestMapping(path = "/ticketPurchase", method = RequestMethod.POST, produces = "application/json")
    public TicketInfo addTicket(@RequestParam(name = "stationOfDepartureId") int stationOfDepartureId,
                            @RequestParam(name = "stationOfArrivalId") int stationOfArrivalId,
                            @RequestParam(name = "trainId") int trainId,
                            @RequestParam(name = "firstName") String firstName,
                            @RequestParam(name = "lastName") String lastName,
                            @RequestParam(name = "dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        TicketInfo ticketInfo = ticketService.create(trainId, stationOfDepartureId, stationOfArrivalId, firstName, lastName, dateOfBirth, name);
        logger.info(String.format("Ticket #%s purchased", ticketInfo.getId()));
        return ticketInfo;
    }

    @RequestMapping(path = "/purchasedTicket", method = RequestMethod.GET)
    public TicketInfo showTicketInfo(@RequestParam(name = "ticketId") int id)
            throws PersistenceException{

        TicketInfo ticketInfo = ticketService.getTicketInfo(id);
        return ticketInfo;
    }

    @RequestMapping(path = "/ticketsPurchased", method = RequestMethod.GET)
    public String showPurchasedTicketsForUser (Model model,
                                        @RequestParam(name = "sinceDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate since,
                                        @RequestParam(name = "untilDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate until) {

        LocalDateTime datetimeSince = null;
        LocalDateTime datetimeUntil = null;
        if (until == null) {
            datetimeUntil = LocalDateTime.now();
        } else {
            datetimeUntil = until.atStartOfDay();
        }
        if (since == null) {
            datetimeSince = datetimeUntil.minusYears(1);
        } else {
            datetimeSince = since.atStartOfDay();
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        model.addAttribute("tickets", ticketService.getTicketInfos(accountService.getByLogin(name).getId(), datetimeSince, datetimeUntil));
        model.addAttribute("sinceDate", datetimeSince.toLocalDate());
        model.addAttribute("untilDate", datetimeUntil.toLocalDate());

        return "ticketsPurchased";
    }

    @RequestMapping(path = "/ticketsPurchasedReport", method = RequestMethod.GET, produces = "application/json")
    public Collection<TicketInfo> showTicketsPurchasedReport(@RequestParam(name = "login") String login,
                                                 @RequestParam(name = "password") String password,
                                                 @RequestParam(name = "since") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate since,
                                                 @RequestParam(name = "until") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate until) {


        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsername(login);
        } catch (UsernameNotFoundException ex) {
            throw new BusinessLogicException("Invalid login or password");
        }
        if (!userDetails.getPassword().equals(password)) {
            throw new BusinessLogicException("Invalid login or password");
        }
        boolean isNotEmployee = userDetails.getAuthorities().stream().filter(authority -> {
            return authority.getAuthority().equals("EMPLOYEE");
        }).count() == 0;
        if (isNotEmployee) {
            throw new BusinessLogicException("Not authorized");
        }
        return null;
    }

}
