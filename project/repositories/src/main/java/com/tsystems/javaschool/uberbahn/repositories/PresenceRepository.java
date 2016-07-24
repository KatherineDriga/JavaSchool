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

    /**
     * Find collection of presences for given station in specified period of time
     * @param stationId station id
     * @param instantSince instatnt since (beginning of period)
     * @param instantUntil instatnt until (period ending)
     * @return collection of presence entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT p FROM Presence AS p WHERE p.spot.station.id = :stationId " +
            "AND p.instant >= :instantSince " +
            "AND p.instant < :instantUntil")
    Collection<Presence> findByStationAndTime(@Param("stationId") int stationId,
                                              @Param("instantSince")Instant instantSince,
                                              @Param("instantUntil")Instant instantUntil);

    /**
     * Find collection of presences for given train
     * @param trainId train id
     * @return collection of presence entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT p FROM Presence AS p WHERE p.train.id = :trainId")
    Collection<Presence> findByTrainId(@Param("trainId") int trainId);

    /**
     * Find presence by train and station
     * @param trainId train id
     * @param stationId station id
     * @return presence entity
     */
    @Transactional(readOnly = true)
    @Query("SELECT p FROM Presence AS p WHERE p.train.id = :trainId AND p.spot.station.id = :stationId")
    Presence findByTrainIdAndStationId(@Param("trainId") int trainId, @Param("stationId") int stationId);

    /**
     * Find presences lying between station of departure and arrival in specified period of time
     * @param stationOfDepartureId id of station of departure
     * @param stationOfArrivalId id of station of arrival
     * @param since since (beginning of period)
     * @param until until (period ending)
     * @return
     */
    @Transactional(readOnly = true)
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


