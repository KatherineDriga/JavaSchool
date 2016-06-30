package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface RouteService {

    RouteInfo getById(int id);
    RouteInfo create (String title, LocalTime timeOfDeparture, List<Integer> stationIds, List<Integer> minutesSinceDepartures, BigDecimal pricePerMinute);




    boolean existsRoute (String title);
    Collection<RouteInfo> getAll ();


}
