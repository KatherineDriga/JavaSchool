package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Station;



public interface StationRepository {

    Station findByTitle(String title);
}
