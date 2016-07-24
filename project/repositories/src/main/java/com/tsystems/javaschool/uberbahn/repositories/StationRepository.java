package com.tsystems.javaschool.uberbahn.repositories;

import com.tsystems.javaschool.uberbahn.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface StationRepository extends JpaRepository<Station, Integer> {

    /**
     * Get station by its title
     * @param title station title
     * @return station entity
     */
    @Transactional(readOnly = true)
    Station findByTitle(String title);


    /**
     * Get all stations ordered by title
     * @return collection of station entities
     */
    @Transactional(readOnly = true)
    Collection<Station> findAllByOrderByTitleAsc();

}
