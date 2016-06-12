package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.transports.StationTimetable;

import java.time.LocalDateTime;

public interface StationService {

    StationTimetable getTimetable (int stationId, LocalDateTime since, LocalDateTime until);

}
