package com.tsystems.javaschool.uberbahn.repositories;

import com.tsystems.javaschool.uberbahn.entities.Ticket;
import com.tsystems.javaschool.uberbahn.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@Transactional
public interface TrainRepository extends JpaRepository<Train, Integer> {

    /**
     * Get purchased tickets for given train
     * @param trainId train id
     * @return collection of ticket entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Ticket AS t WHERE t.train.id = :trainId")
    Collection<Ticket> getTicketsByTrainId(@Param("trainId") int trainId);


    /**
     * Get train by route and date of departure
     * @param routeId route id
     * @param dateOfDeparture date of departure
     * @return train entity
     */
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId AND t.dateOfDeparture = :dateOfDeparture")
    Train findByRouteIdAndDateOfDeparture(@Param("routeId") int routeId, @Param("dateOfDeparture") LocalDate dateOfDeparture);


    /**
     * Get trains by route
     * @param routeId route id
     * @return collection of train entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Train AS t WHERE t.route.id = :routeId")
    Collection<Train> findByRouteId(@Param("routeId") int routeId);

    /**
     * Get trains which are not archived
     * @return collection of train entities
     */
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Train AS t WHERE t.archived = false")
    Collection<Train> findNotArchived();

}

