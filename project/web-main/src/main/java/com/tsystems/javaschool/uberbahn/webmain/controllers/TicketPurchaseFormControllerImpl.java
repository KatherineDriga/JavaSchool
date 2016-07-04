package com.tsystems.javaschool.uberbahn.webmain.controllers;


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

    @RequestMapping(path = "/ticketPurchaseForm", method = RequestMethod.GET)
    public ModelAndView ticketPurchaseForm(@RequestParam(name = "stationOfDeparture") int stationOfDepartureId,
                                           @RequestParam(name = "stationOfArrival") int stationOfArrivalId,
                                           @RequestParam(name = "trainId") int trainId) {

        ModelAndView model = new ModelAndView();
        model.addObject("stationOfDepartureId", stationOfDepartureId);
        model.addObject("stationOfArrivalId", stationOfArrivalId);
        model.addObject("trainId", trainId);
        model.setViewName("ticketPurchaseForm");
        return model;
    }

}
