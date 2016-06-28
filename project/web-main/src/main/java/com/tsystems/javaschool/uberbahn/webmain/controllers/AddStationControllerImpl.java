package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/*@Controller
@RequestMapping("/")
public class AddStationControllerImpl {

    private final StationService stationService;

    @Autowired
    public AddStationControllerImpl(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(path = "/addStation", method = RequestMethod.POST, consumes="application/json")
    public String addStation(Model model) {

        LocalDate sinceDate = LocalDate.now();
        LocalDate untilDate = sinceDate.plusDays(7);

        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("sinceDate", sinceDate);
        model.addAttribute("untilDate", untilDate);

        return "trainTimetableSearch";
    }
}*/
public class AddStationControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stationTitle = getRequiredParameter("stationTitle", req);


        StationInfo stationInfo = runTransaction((session -> {

            StationService service = null; // TODO: with DI
            return service.getStationInfo(stationTitle);
        }));

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        mapper.writeValue(out, stationInfo);
    }
}
