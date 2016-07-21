package com.tsystems.javaschool.uberbahn.repositories;

import com.tsystems.javaschool.uberbahn.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Transactional(readOnly = true)
    Collection<Route> findAllByOrderByTitleAsc();

    @Transactional(readOnly = true)
    @Query("SELECT r FROM Route AS r JOIN r.spots AS s WHERE s.station.id = :stationId")
    Collection<Route> findByStationId (@Param("stationId") int stationId);

    @Transactional(readOnly = true)
    Route findByTitle(String title);

}
