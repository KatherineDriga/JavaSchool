package com.tsystems.javaschool.uberbahn.repositories;


import com.tsystems.javaschool.uberbahn.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket AS t WHERE t.train.id = :trainId AND t.stationOfDeparture.id = :stationOfDepartureId")
    Collection<Ticket> getByTrainIdAndStationOfDeparture(@Param("trainId") int trainId, @Param("stationOfDepartureId") int stationOfDepartureId);

    @Query("SELECT t FROM Ticket AS t " +
            "WHERE t.account.id = :accountId " +
            "AND t.datetimeOfPurchase >= :since " +
            "AND t.datetimeOfPurchase < :until")
    Collection<Ticket> getByAccountIdSinceAndUntil(@Param("accountId") int id,
                                                   @Param("since") LocalDateTime since,
                                                   @Param("until") LocalDateTime until);

    @Query("SELECT t FROM Ticket AS t " +
            "WHERE t.datetimeOfPurchase >= :since " +
            "AND t.datetimeOfPurchase < :until")
    Collection<Ticket> getBySinceAndUntil(@Param("since") LocalDateTime since,
                                          @Param("until") LocalDateTime until);
}

