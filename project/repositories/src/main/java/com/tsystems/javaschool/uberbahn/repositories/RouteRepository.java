package com.tsystems.javaschool.uberbahn.repositories;

import com.tsystems.javaschool.uberbahn.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface RouteRepository extends JpaRepository<Route, Integer> {

    /**
     * Get all routes ordered by title
     * @return collection of route entities
     */
    @Transactional(readOnly = true)
    Collection<Route> findAllByOrderByTitleAsc();


    /**
     * Get route by its title
     * @param title
     * @return route entity
     */
    @Transactional(readOnly = true)
    Route findByTitle(String title);

}
