package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Collection;

public interface PresenceRepository extends JpaRepository<Presence, Integer> {

    @Query("SELECT p FROM Presence AS p WHERE p.trainId = :trainId AND p.spotId = :spotId")
    Presence findByTrainIdAndSpotId(@Param("trainId") int trainId, @Param("spotId") int spotId);

    @Query("SELECT p FROM Presence AS p WHERE p.trainId = :trainId " +
            "AND p.instant >= :instantDeparture " +
            "AND p.instant <= :instantArrival")
    Collection<Presence> findAllBetweenStationsByTrainIdAndInstant(@Param("trainId") int trainId,
                                                                   @Param("instantDeparture")Instant instantDeparture,
                                                                   @Param("instantArrival")Instant instantArrival);
}


