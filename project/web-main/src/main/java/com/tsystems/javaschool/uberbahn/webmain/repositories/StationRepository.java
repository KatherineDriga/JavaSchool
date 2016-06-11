package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Station;



public interface StationRepository extends BaseRepository<Station> {

    Station findByTitle(String title);
}
