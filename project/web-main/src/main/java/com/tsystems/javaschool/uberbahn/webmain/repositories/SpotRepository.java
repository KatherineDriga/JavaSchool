package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;

import java.util.Collection;
import java.util.List;

public interface SpotRepository extends BaseRepository<Spot> {
    Spot findByStationIdAndRouteId (int stationId, int routeId);
    Collection<Spot> findAllBetweenStationsByRouteIdAndTime(int routeId, Integer timeSinceDepartureForStA, Integer timeSinceDepartureForStB);

}
