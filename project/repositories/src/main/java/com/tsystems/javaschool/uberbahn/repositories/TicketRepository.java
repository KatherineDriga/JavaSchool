package com.tsystems.javaschool.uberbahn.repositories;


import com.tsystems.javaschool.uberbahn.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket AS t WHERE t.train.id = :trainId AND t.stationOfDeparture.id = :stationOfDepartureId")
    Collection<Ticket> getByTrainIdAndStationOfDeparture(@Param("trainId") int trainId, @Param("stationOfDepartureId") int stationOfDepartureId);
}

