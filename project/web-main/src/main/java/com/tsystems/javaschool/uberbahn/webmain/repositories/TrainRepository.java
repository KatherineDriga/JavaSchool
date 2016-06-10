package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Train;

import java.util.Collection;


public interface TrainRepository {

    Collection<Train> findByRoute(int routeId);
}

