package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;


public interface StationRepository extends JpaRepository<Station, Integer> {

    Station findByTitle(String title);

    Collection<Station> findAllByOrderByTitleAsc();

}
