package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;

import java.util.Collection;

public interface RouteService {

    boolean existsRoute (String title);
    Collection<RouteInfo> getAll ();

}
