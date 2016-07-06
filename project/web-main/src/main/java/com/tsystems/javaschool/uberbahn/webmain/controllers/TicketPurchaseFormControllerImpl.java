package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class TicketPurchaseFormControllerImpl {

    /*@RequestMapping(path = "/ticketPurchaseForm", method = RequestMethod.GET)
    public String ticketPurchaseForm(Model model, @RequestParam(name = "stationOfDeparture") int stationOfDepartureId,
                                     @RequestParam(name = "stationOfArrival") int stationOfArrivalId,
                                     @RequestParam(name = "trainId") int trainId) {

        model.addAttribute("stationOfDepartureId", stationOfDepartureId);
        model.addAttribute("stationOfArrivalId", stationOfArrivalId);
        model.addAttribute("trainId", trainId);
        return "ticketPurchaseForm";
    }*/
    private final AccountService accountService;

    @Autowired
    public TicketPurchaseFormControllerImpl (AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/ticketPurchaseForm", method = RequestMethod.GET)
    public ModelAndView ticketPurchaseForm(@RequestParam(name = "stationOfDeparture") int stationOfDepartureId,
                                           @RequestParam(name = "stationOfArrival") int stationOfArrivalId,
                                           @RequestParam(name = "trainId") int trainId) {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        model.addObject("stationOfDepartureId", stationOfDepartureId);
        model.addObject("stationOfArrivalId", stationOfArrivalId);
        model.addObject("trainId", trainId);
        AccountDetails accountDetails = accountService.getByLogin(userName);
        if (!accountDetails.isEmployee()){
            model.addObject("account", accountService.getByLogin(userName));
        }
        model.setViewName("ticketPurchaseForm");
        return model;
    }

}
