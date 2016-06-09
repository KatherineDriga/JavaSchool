package com.tsystems.javaschool.uberbahn.webmain.services;

import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;


public class StationServiceImpl extends BaseServiceImpl implements StationService{


    public StationServiceImpl(Session session) {
        super(session);
    }

    @Override
    public Collection<StationScheduleEvent> getScheduleEvents(int stationId,
                                                              LocalDate sinceDate,
                                                              LocalTime sinceTime,
                                                              LocalDate untilDate,
                                                              LocalTime untilTime) {
        return null;
    }
}
