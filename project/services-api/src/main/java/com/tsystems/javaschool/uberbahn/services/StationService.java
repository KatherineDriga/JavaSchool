package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.transports.StationTimetable;

import java.time.LocalDateTime;
import java.util.Collection;

public interface StationService {

    StationInfo create(String stationTitle, int timezone);

    StationTimetable getTimetable (int stationId, LocalDateTime since, LocalDateTime until);

    Collection<StationInfo> getAll ();

    boolean existsStation (String title);

}
