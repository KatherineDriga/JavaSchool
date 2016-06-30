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

    /*@Query("SELECT DISTINCT r FROM Route AS r JOIN r.spots AS s1 JOIN r.spots AS s2 " +
            "WHERE s1.station.id = :stationOfDepartureId " +
            "AND s2.station.id = :stationOfArrivalId")
    Collection<Route> findByDepartureAndArrival(
            @Param("stationOfDepartureId") int stationOfDepartureId,
            @Param("stationOfArrivalId") int stationOfArrivalId);*/
}
