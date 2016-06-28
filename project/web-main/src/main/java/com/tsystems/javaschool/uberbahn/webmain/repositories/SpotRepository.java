package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Integer> {

    @Query("SELECT s FROM Spot AS s WHERE s.station.id = :stationId AND s.route.id = :routeId")
    Spot findByStationIdAndRouteId (@Param("stationId") int stationId, @Param("routeId") int routeId);

    @Query("SELECT s FROM Spot AS s WHERE s.station.id = :stationId")
    Collection<Spot> findByStationId (@Param("stationId") int stationId);
    /*
    Collection<Spot> findAllBetweenStationsByRouteIdAndTime(int routeId, Integer timeSinceDepartureForStA, Integer timeSinceDepartureForStB);
    */

}


