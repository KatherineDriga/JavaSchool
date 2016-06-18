package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface RouteService {

    boolean existsRoute (String title);
    Collection<RouteInfo> getAll ();
    RouteInfo create (String title, LocalTime timeOfDeparture, List<Integer> stationIds, List<Integer> minutesSinceDepartures);
    RouteInfo getById (int id);

}
