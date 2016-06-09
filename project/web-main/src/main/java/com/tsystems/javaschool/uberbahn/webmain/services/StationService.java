package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface StationService {

    Collection<StationScheduleEvent> getScheduleEvents(int stationId,
                                                       LocalDate sinceDate,
                                                       LocalTime sinceTime,
                                                       LocalDate untilDate,
                                                       LocalTime untilTime);

}
