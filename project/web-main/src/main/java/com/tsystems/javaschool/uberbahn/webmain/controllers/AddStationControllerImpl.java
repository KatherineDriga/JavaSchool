package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.tsystems.javaschool.uberbahn.webmain.services.StationService;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AddStationControllerImpl {

    private final StationService stationService;

    @Autowired
    public AddStationControllerImpl(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(path = "/addStation", method = RequestMethod.POST, produces = "application/json")
    public StationInfo addStation(@RequestParam(name = "stationTitle") String stationTitle,
                                  @RequestParam(name = "timezone") int timezone) {

        StationInfo stationInfo = stationService.create(stationTitle, timezone);
        return stationInfo;
    }
}
/*public class AddStationControllerImpl extends BaseControllerImpl {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stationTitle = getRequiredParameter("stationTitle", req);


        StationInfo stationInfo = runTransaction((session -> {

            StationService service = null; // TODO: with DI
            return service.create(stationTitle);
        }));

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        mapper.writeValue(out, stationInfo);
    }
}*/
