package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;

import java.util.Collection;
import java.util.List;


public interface RouteRepository extends BaseRepository<Route> {

    Collection<Route> findByStationId (int stationId);
    Route findByTitle (String routeTitle);
    Collection<Route> findAll();
}
