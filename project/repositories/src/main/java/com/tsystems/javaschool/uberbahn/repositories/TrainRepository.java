package com.tsystems.javaschool.uberbahn.repositories;

import com.tsystems.javaschool.uberbahn.entities.Presence;
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

    @Transactional(readOnly = true)
    @Query("SELECT t FROM Ticket AS t WHERE t.train.id = :trainId")
    Collection<Ticket> getTicketsByTrainId(@Param("trainId") int trainId);

    @Transactional(readOnly = true)
    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId AND t.dateOfDeparture = :dateOfDeparture")
    Train findByRouteIdAndDateOfDeparture(@Param("routeId") int routeId, @Param("dateOfDeparture") LocalDate dateOfDeparture);

    @Transactional(readOnly = true)
    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId")
    Collection<Train> findByRouteId(@Param("routeId") int routeId);

    @Transactional(readOnly = true)
    @Query("SELECT t FROM Train AS t WHERE t.archived = false")
    Collection<Train> findNotArchived();

}

