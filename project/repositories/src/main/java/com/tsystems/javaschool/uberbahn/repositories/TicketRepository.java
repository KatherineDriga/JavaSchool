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

    /**
     * Get tickets purchased with given account in specified period of time
     * @param id account id
     * @param since date and time of purchase (beginning of period)
     * @param until date and time of purchase (period ending)
     * @return collection of ticket entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Ticket AS t " +
            "WHERE t.account.id = :accountId " +
            "AND t.datetimeOfPurchase >= :since " +
            "AND t.datetimeOfPurchase < :until")
    Collection<Ticket> getByAccountIdSinceAndUntil(@Param("accountId") int id,
                                                   @Param("since") LocalDateTime since,
                                                   @Param("until") LocalDateTime until);

    /**
     * Get tickets purchased in specified period of time
     * @param since date and time of purchase (beginning of period)
     * @param until date and time of purchase (period ending)
     * @return collection of ticket entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Ticket AS t " +
            "WHERE t.datetimeOfPurchase >= :since " +
            "AND t.datetimeOfPurchase < :until")
    Collection<Ticket> getBySinceAndUntil(@Param("since") LocalDateTime since,
                                          @Param("until") LocalDateTime until);
}

