package com.tsystems.javaschool.uberbahn.services;

import com.tsystems.javaschool.uberbahn.entities.Presence;
import com.tsystems.javaschool.uberbahn.entities.Spot;
import com.tsystems.javaschool.uberbahn.entities.Station;
import com.tsystems.javaschool.uberbahn.repositories.PresenceRepository;
import com.tsystems.javaschool.uberbahn.repositories.StationRepository;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.services.errors.DatabaseException;
import com.tsystems.javaschool.uberbahn.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.transports.StationScheduleEvent;
import com.tsystems.javaschool.uberbahn.transports.StationTimetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.time.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final PresenceRepository presenceRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository, PresenceRepository presenceRepository) {
        this.stationRepository = stationRepository;
        this.presenceRepository = presenceRepository;
    }

    @Override
    public StationInfo create(String stationTitle, int timezone) {
        String message = checkFields(stationTitle, timezone);
        if (message != "checked") {
            throw new BusinessLogicException(message);
        }
        Station station = new Station();
        station.setTitle(stationTitle);
        station.setTimezone(timezone);
        try {
            stationRepository.save(station);
        } catch (PersistenceException ex) {
            throw new DatabaseException("Error occurred", ex);
        }
        StationInfo stationInfo = new StationInfo();
        stationInfo.setId(station.getId());
        stationInfo.setTitle(stationTitle);
        return stationInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<StationInfo> getAll() {
        return stationRepository.findAllByOrderByTitleAsc().stream().map(station -> {
            StationInfo info = new StationInfo();
            info.setId(station.getId());
            info.setTitle(station.getTitle());
            return info;
        }).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public StationTimetable getTimetable(int stationId, LocalDateTime since, LocalDateTime until) {
        StationTimetable timetable = new StationTimetable();
        Station station = stationRepository.findOne(stationId);
        timetable.setTitle(station.getTitle());

        Instant instantSince = since.toInstant(ZoneOffset.ofHours(station.getTimezone()));
        Instant instantUntil = until.toInstant(ZoneOffset.ofHours(station.getTimezone()));

        Collection<Presence> presences = presenceRepository.findByStationAndTime(stationId, instantSince, instantUntil);

        List<StationScheduleEvent> events = presences.stream().map(presence -> {
            StationScheduleEvent event = new StationScheduleEvent();
            OffsetDateTime datetime = presence.getInstant().atOffset(ZoneOffset.ofHours(station.getTimezone()));
            event.setDate(datetime.toLocalDate());
            event.setTime(datetime.toLocalTime());
            event.setRoute(presence.getTrain().getRoute().getTitle());
            List<Spot> spots = presence.getTrain().getRoute().getSpots();
            event.setDepartsFrom(spots.get(0).getStation().getTitle());
            event.setArrivesAt(spots.get(spots.size() - 1).getStation().getTitle());
            event.setTrain(presence.getTrain().getId());
            return event;
        }).collect(Collectors.toList());
        Collections.sort(events, (StationScheduleEvent s1, StationScheduleEvent s2) ->
                s1.getDate().atTime(s1.getTime()).compareTo(s2.getDate().atTime(s2.getTime())));
        timetable.setScheduleEvents(events);

        return timetable;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsStation(String title) {
        if (stationRepository.findByTitle(title) != null) {
            return true;
        }
        return false;
    }

    private String checkFields(String stationTitle, int timezone) {
        if (stationTitle == null) {
            return "Station title required";
        }
        if (timezone > 14 || timezone < -12) {
            return "Invalid timezone";
        }
        if (existsStation(stationTitle)) {
            return String.format("Station %s already exists", stationTitle);
        }
        return "checked";
    }
}

