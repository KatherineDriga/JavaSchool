package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationTimetable;

import java.time.LocalDateTime;
import java.util.Collection;

public interface StationService {

    StationInfo create(String stationTitle, int timezone);

    StationTimetable getTimetable (int stationId, LocalDateTime since, LocalDateTime until);

    Collection<StationInfo> getAll ();

}
