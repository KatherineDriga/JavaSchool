package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationTimetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface StationService {

    Collection<StationScheduleEvent> getScheduleEvents(int stationId,
                                                       LocalDateTime sinceDateTime,
                                                       LocalDateTime untilDateTime);
    StationTimetable getTimetable (int stationId, LocalDateTime since, LocalDateTime until);

}
