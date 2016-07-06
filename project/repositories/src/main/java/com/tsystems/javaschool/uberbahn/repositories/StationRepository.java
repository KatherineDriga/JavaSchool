package com.tsystems.javaschool.uberbahn.repositories;

import com.tsystems.javaschool.uberbahn.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface StationRepository extends JpaRepository<Station, Integer> {

    Station findByTitle(String title);

    Collection<Station> findAllByOrderByTitleAsc();

}
