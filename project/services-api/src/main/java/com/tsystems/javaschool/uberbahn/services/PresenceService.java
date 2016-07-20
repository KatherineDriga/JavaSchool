package com.tsystems.javaschool.uberbahn.services;


public interface PresenceService {

    /**
     * Method marks trains which arrived at final station as archived,
     * update them in database and drops their presences
     */
    void archive();

}
