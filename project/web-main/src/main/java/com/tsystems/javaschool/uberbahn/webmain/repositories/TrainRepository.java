package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import com.tsystems.javaschool.uberbahn.webmain.entities.Ticket;
import com.tsystems.javaschool.uberbahn.webmain.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

}

