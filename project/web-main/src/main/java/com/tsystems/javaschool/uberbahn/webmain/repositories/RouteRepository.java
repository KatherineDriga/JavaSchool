package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("SELECT r FROM Route AS r JOIN r.spots AS s WHERE s.station.id = :stationId")
    Collection<Route> findByStationId (@Param("stationId") int stationId);

    Route findByTitle(String title);
}
