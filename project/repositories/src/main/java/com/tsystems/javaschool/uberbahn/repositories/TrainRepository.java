package com.tsystems.javaschool.uberbahn.repositories;

import com.tsystems.javaschool.uberbahn.entities.Ticket;
import com.tsystems.javaschool.uberbahn.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Transactional
public interface TrainRepository extends JpaRepository<Train, Integer> {

    @Query("SELECT t FROM Ticket AS t WHERE t.train.id = :trainId")
    Collection<Ticket> getTicketsByTrainId(@Param("trainId") int trainId);

    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId AND t.dateOfDeparture = :dateOfDeparture")
    Train findByRouteIdAndDateOfDeparture(@Param("routeId") int routeId, @Param("dateOfDeparture") LocalDate dateOfDeparture);

    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId")
    Collection<Train> findByRouteId(@Param("routeId") int routeId);

    @Query(value = "SELECT t.id, t.routeId, t.dateOfDeparture, t.numberOfSeats, t.priceCoefficient, t.archived " +
                                "FROM train AS t " +
                                "JOIN presence AS p1 " +
                                "ON t.id=p1.trainId " +
                                "JOIN presence AS p2 " +
                                "ON p1.trainId=p2.trainId " +
                                "JOIN spot AS s1 " +
                                "ON s1.id=p1.spotId " +
                                "JOIN spot AS s2 " +
                                "ON s2.id=p2.spotId " +
                                "WHERE s1.stationId = :stationOfDepartureId " +
                                "AND s2.stationId = :stationOfArrivalId " +
                                "AND p1.instant >= :since " +
                                "AND p1.instant < :until",
            nativeQuery = true)
    Collection<Train> findByDepartureArrivalAndTime(
            @Param("stationOfDepartureId") int stationOfDepartureId,
            @Param("stationOfArrivalId") int stationOfArrivalId,
            @Param("since") Instant since,
            @Param("until") Instant until);

    @Query(value = "SELECT t.id " +
            "FROM train AS t " +
            "JOIN presence AS p1 " +
            "ON t.id=p1.trainId " +
            "JOIN presence AS p2 " +
            "ON p1.trainId=p2.trainId " +
            "JOIN spot AS s1 " +
            "ON s1.id=p1.spotId " +
            "JOIN spot AS s2 " +
            "ON s2.id=p2.spotId " +
            "WHERE s1.stationId = :stationOfDepartureId " +
            "AND s2.stationId = :stationOfArrivalId " +
            "AND p1.instant >= :since " +
            "AND p1.instant < :until",
            nativeQuery = true)
    Set<Integer> getTrainIds(@Param("stationOfDepartureId") int stationOfDepartureId,
                             @Param("stationOfArrivalId") int stationOfArrivalId,
                             @Param("since") Instant since,
                             @Param("until") Instant until);

    @Query("SELECT t1 FROM Train AS t1 JOIN t1.presences AS p1 JOIN p1.train AS t2 JOIN t2.presences AS p2 " +
            "WHERE p1.spot.station.id = :stationOfDepartureId " +
            "AND p2.spot.station.id = :stationOfArrivalId " +
            "AND p1.instant >= :since AND p1.instant < :until")
    Collection<Train> findByDepartureArrivalAndTime2(
            @Param("stationOfDepartureId") int stationOfDepartureId,
            @Param("stationOfArrivalId") int stationOfArrivalId,
            @Param("since") Instant since,
            @Param("until") Instant until);

    /*@Query("SELECT t FROM Train AS t JOIN t.presences AS p1 JOIN t.presences AS p2 " +
            "WHERE p1.spot.station.id = :stationOfDepartureId " +
            "AND p2.spot.station.id = :stationOfArrivalId " +
            "AND p1.instant >= :since AND p1.instant < :until")
    Collection<Train> findByDepartureArrivalAndTime(
            @Param("stationOfDepartureId") int stationOfDepartureId,
            @Param("stationOfArrivalId") int stationOfArrivalId,
            @Param("since") Instant since,
            @Param("until") Instant until);*/

}

