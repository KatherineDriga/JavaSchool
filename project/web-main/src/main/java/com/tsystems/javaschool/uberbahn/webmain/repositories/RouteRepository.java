package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;

import java.util.Collection;

/**
 * Created by ASUS on 09.06.2016.
 */
public interface RouteRepository extends BaseRepository<Route> {

    Collection<Route> findByStationId (int stationId);
}
