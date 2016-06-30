package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import com.tsystems.javaschool.uberbahn.webmain.entities.Ticket;
import com.tsystems.javaschool.uberbahn.webmain.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;


public interface TrainRepository extends JpaRepository<Train, Integer> {

    @Query("SELECT t FROM Ticket AS t WHERE t.train.id = :trainId")
    Collection<Ticket> getTicketsByTrainId(@Param("trainId") int trainId);

    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId AND t.dateOfDeparture = :dateOfDeparture")
    Train findByRouteIdAndDateOfDeparture(@Param("routeId") int routeId, @Param("dateOfDeparture") LocalDate dateOfDeparture);

    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId")
    Collection<Train> findByRouteId(@Param("routeId") int routeId);

    @Query("SELECT t FROM Train AS t JOIN t.presences AS p1 JOIN t.presences AS p2 " +
            "WHERE p1.spot.station.id = :stationOfDepartureId " +
            "AND p2.spot.station.id = :stationOfArrivalId " +
            "AND p1.instant >= :since AND p1.instant <= :until " +
            "AND p1.spot.route.id = p2.spot.route.id")
    Collection<Train> findByDepartureArrivalAndTime(
            @Param("stationOfDepartureId") int stationOfDepartureId,
            @Param("stationOfArrivalId") int stationOfArrivalId,
            @Param("since") Instant since,
            @Param("until") Instant until);

    /*@Query("SELECT t FROM Train AS t JOIN t.presences AS p " +
            "WHERE p.spot.route.id = :routeId " +
            "AND p.instant >= :since AND p.instant < :until ")
    Collection<Train> findByRouteAndTime(
                    @Param("routeId") int routeId,
                    @Param("since") Instant since,
                    @Param("until") Instant until);*/

}

