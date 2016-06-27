package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SpotRepository extends BaseRepository<Spot> {

    @Query("SELECT s FROM Spot AS s WHERE s.stationId = :stationIdId AND s.routeId = :routeId")
    Spot findByStationIdAndRouteId (@Param("stationId") int stationId, @Param("routeId") int routeId);

    /*
    Collection<Spot> findAllBetweenStationsByRouteIdAndTime(int routeId, Integer timeSinceDepartureForStA, Integer timeSinceDepartureForStB);
    */

}


