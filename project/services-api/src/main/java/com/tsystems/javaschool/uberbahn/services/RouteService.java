package com.tsystems.javaschool.uberbahn.services;



import com.tsystems.javaschool.uberbahn.transports.RouteInfo;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface RouteService {

    /**
     * Method gets route from database by its id and returns route details
     * @param id route id (primary key)
     * @return data transfer object containing route information
     */
    RouteInfo getById(int id);


    /**
     * Method creates new route, saves it in database and returns route details
     * @param title route title
     * @param timeOfDeparture local time of departure
     * @param stationIds collection if station ids passing by route
     * @param minutesSinceDepartures collection if minutes since departure (minutes since departure until arriving at station)
     * @param pricePerMinute price per minute of travel time
     * @return data transfer object containing saved route information
     */
    RouteInfo create (String title, LocalTime timeOfDeparture, List<Integer> stationIds, List<Integer> minutesSinceDepartures, BigDecimal pricePerMinute);


    /**
     * Method checks whether route with such title already exists
     * @param title route title
     * @return boolean value
     */
    boolean existsRoute (String title);


    /**
     * Method returns collection of route details for existing routes
     * @return collection of data transfer objects containing route information
     */
    Collection<RouteInfo> getAll ();

}
