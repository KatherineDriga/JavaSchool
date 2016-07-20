package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.transports.StationTimetable;

import java.time.LocalDateTime;
import java.util.Collection;

public interface StationService {

    /**
     * Method creates new station, saves it in database and returns station details
     * @param stationTitle station title
     * @param timezone number of hours by which station time ahead of or behind Coordinated Universal Time (UTC)
     * @return data transfer object containing saved station information
     */
    StationInfo create(String stationTitle, int timezone);


    /**
     * Method returns station timetable per period
     * @param stationId station id (primary key)
     * @param since date and time since
     * @param until date and time until
     * @return data transfer object containing station timetable
     */
    StationTimetable getTimetable (int stationId, LocalDateTime since, LocalDateTime until);


    /**
     *Method returns collection of station details for existing stations
     * @return collection of data transfer objects containing station information
     */
    Collection<StationInfo> getAll ();


    /**
     * Method checks whether station with such title already exists
     * @param title
     * @return boolean value
     */
    boolean existsStation (String title);

}
