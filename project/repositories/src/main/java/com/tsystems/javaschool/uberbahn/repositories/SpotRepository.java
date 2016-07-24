package com.tsystems.javaschool.uberbahn.repositories;


import com.tsystems.javaschool.uberbahn.entities.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface SpotRepository extends JpaRepository<Spot, Integer> {

    /**
     * Get spot by its station and route
     * @param stationId station id
     * @param routeId route id
     * @return spot entity
     */
    @Transactional(readOnly = true)
    @Query("SELECT s FROM Spot AS s WHERE s.station.id = :stationId AND s.route.id = :routeId")
    Spot findByStationIdAndRouteId (@Param("stationId") int stationId, @Param("routeId") int routeId);


    /**
     * Get collection of spots for given route
     * @param routeId route id
     * @return collection of spot entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT s FROM Spot AS s WHERE s.route.id = :routeId")
    Collection<Spot> findByRouteId (@Param("routeId") int routeId);

}


