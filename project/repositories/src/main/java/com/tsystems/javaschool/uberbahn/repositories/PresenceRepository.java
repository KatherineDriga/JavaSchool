package com.tsystems.javaschool.uberbahn.repositories;


import com.tsystems.javaschool.uberbahn.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;

@Transactional
public interface PresenceRepository extends JpaRepository<Presence, Integer> {

    @Query("SELECT p FROM Presence AS p WHERE p.train.id = :trainId AND p.spot.id = :spotId")
    Presence findByTrainIdAndSpotId(@Param("trainId") int trainId, @Param("spotId") int spotId);


    @Query("SELECT p FROM Presence AS p WHERE p.spot.station.id = :stationId " +
            "AND p.instant >= :instantSince " +
            "AND p.instant < :instantUntil")
    Collection<Presence> findByStationAndTime(@Param("stationId") int stationId,
                                              @Param("instantSince")Instant instantSince,
                                              @Param("instantUntil")Instant instantUntil);

    @Query("SELECT p FROM Presence AS p WHERE p.train.id = :trainId")
    Collection<Presence> findByTrainId(@Param("trainId") int trainId);

    @Query("SELECT p FROM Presence AS p WHERE p.train.id = :trainId AND p.spot.station.id = :stationId")
    Presence findByTrainIdAndStationId(@Param("trainId") int trainId, @Param("stationId") int stationId);

    @Query("SELECT p1 FROM Presence AS p1, Presence AS p2 " +
            "WHERE p1.instant >= :since " +
            "AND p1.instant < :until " +
            "AND p1.instant < p2.instant " +
            "AND p1.spot.station.id = :stationOfDepartureId " +
            "AND p2.spot.station.id = :stationOfArrivalId " +
            "AND p1.train.id = p2.train.id")
    Collection<Presence> findByDepartureArrivalStationAndTime(
            @Param("stationOfDepartureId") int stationOfDepartureId,
            @Param("stationOfArrivalId") int stationOfArrivalId,
            @Param("since") Instant since,
            @Param("until") Instant until);
}


