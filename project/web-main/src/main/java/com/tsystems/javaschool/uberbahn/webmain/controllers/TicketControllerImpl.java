package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.services.TicketService;
import com.tsystems.javaschool.uberbahn.services.TrainService;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import com.tsystems.javaschool.uberbahn.transports.TicketInfo;
import com.tsystems.javaschool.uberbahn.transports.TicketReport;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Controller
public class TicketControllerImpl {

    private final TicketService ticketService;
    private final AccountService accountService;
    private final UserDetailsService userDetailsService;
    private final TrainService trainService;
    private final Logger logger = LogManager.getLogger(TrainTimetableControllerImpl.class);

    @Autowired
    public TicketControllerImpl(TicketService ticketService, AccountService accountService, UserDetailsService userDetailsService, TrainService trainService) {
        this.ticketService = ticketService;
        this.accountService = accountService;
        this.userDetailsService = userDetailsService;
        this.trainService = trainService;
    }

    @RequestMapping(path = "/ticketPurchaseForm", method = RequestMethod.GET)
    public String showTicketPurchaseForm(Model model, @RequestParam(name = "stationOfDeparture") int stationOfDepartureId,
                                         @RequestParam(name = "stationOfArrival") int stationOfArrivalId,
                                         @RequestParam(name = "trainId") int trainId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        TrainInfo trainInfo = trainService.getByDepartureArrivalAndTrainId(stationOfDepartureId, stationOfArrivalId, trainId);
        model.addAttribute("stationOfDepartureId", stationOfDepartureId);
        model.addAttribute("stationOfArrivalId", stationOfArrivalId);
        model.addAttribute("trainId", trainId);
        model.addAttribute("trainInfo", trainInfo);
        AccountDetails accountDetails = accountService.getByLogin(userName);
        if (!accountDetails.isEmployee()) {
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
    public TicketInfo showTicketInfo(@RequestParam(name = "ticketId") int id) {

        return ticketService.getTicketInfo(id);
    }

    @RequestMapping(path = "/ticketsPurchased", method = RequestMethod.GET)
    public String showPurchasedTicketsForUser(Model model,
                                              @RequestParam(name = "sinceDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate since,
                                              @RequestParam(name = "untilDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate until) {

        LocalDateTime datetimeSince;
        LocalDateTime datetimeUntil;
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
    @ResponseBody
    public Collection<TicketReport> showTicketsPurchasedReport
            (@RequestParam(name = "login") String login,
             @RequestParam(name = "password") String password,
             @RequestParam(name = "since", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate since,
             @RequestParam(name = "until", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate until) {


        UserDetails userDetails = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            userDetails = userDetailsService.loadUserByUsername(login);
        } catch (UsernameNotFoundException ex) {
            throw new BusinessLogicException("Invalid login or password");
        }
        if (!(encoder.matches(password, userDetails.getPassword()))) {
            throw new BusinessLogicException("Invalid login or password");
        }
        boolean isNotEmployee = userDetails.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().equals("EMPLOYEE")).count() == 0;

        if (isNotEmployee) {
            throw new BusinessLogicException("Not authorized");
        }

        LocalDateTime datetimeSince;
        LocalDateTime datetimeUntil;
        if (until == null) {
            datetimeUntil = LocalDateTime.now();
        } else {
            datetimeUntil = until.atStartOfDay();
        }
        if (since == null) {
            datetimeSince = datetimeUntil.minusDays(7);
        } else {
            datetimeSince = since.atStartOfDay();
        }

        return ticketService.getTicketInfos(datetimeSince, datetimeUntil);

    }

}
