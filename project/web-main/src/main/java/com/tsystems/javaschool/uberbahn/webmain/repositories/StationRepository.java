package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Station;

import java.util.Collection;


public interface StationRepository extends BaseRepository<Station> {

    Station findByTitle(String title);
    Collection<Station> findAll();
}
