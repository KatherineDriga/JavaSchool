package com.tsystems.javaschool.uberbahn.webmain.services;

import com.tsystems.javaschool.uberbahn.webmain.entities.*;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationTimetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final PresenceRepository presenceRepository;
    private final TrainRepository trainRepository;

    @Autowired
    public StationServiceImpl(RouteRepository routeRepository, StationRepository stationRepository, PresenceRepository presenceRepository, TrainRepository trainRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.presenceRepository = presenceRepository;
        this.trainRepository = trainRepository;
    }
    @Override
    public StationInfo create(String stationTitle, int timezone) {

        Station station = new Station();
        Collection<Spot> spots = null;
        Collection<Ticket> departures = null;
        Collection<Ticket> arrivals = null;
        station.setTitle(stationTitle);
        station.setSpots(spots);
        station.setDepartures(departures);
        station.setArrivals(arrivals);
        station.setTimezone(timezone);

        int stationId = stationRepository.save(station).getId();

        StationInfo stationInfo = new StationInfo();
        stationInfo.setId(stationId);
        stationInfo.setTitle(stationTitle);

        return stationInfo;
    }

    @Override
    public Collection<StationInfo> getAll() {
        Collection<StationInfo> result = new ArrayList<>();

        stationRepository.findAll().forEach(station -> {
            StationInfo info = new StationInfo();
            info.setId(station.getId());
            info.setTitle(station.getTitle());
            result.add(info);
        });
        return result;
    }


    @Override
    public StationTimetable getTimetable(int stationId, LocalDateTime since, LocalDateTime until) {
        StationTimetable timetable = new StationTimetable();
        Collection<StationScheduleEvent> events = new ArrayList<>();
        Station station = stationRepository.findOne(stationId);
        timetable.setTitle(station.getTitle());
        timetable.setScheduleEvents(events);

        Instant instantSince = since.toInstant(ZoneOffset.ofHours(station.getTimezone()));
        Instant instantUntil = until.toInstant(ZoneOffset.ofHours(station.getTimezone()));

        Collection<Presence> presences = presenceRepository.findByStationAndTime(stationId, instantSince, instantUntil);

        presences.forEach(presence -> {
            StationScheduleEvent event = new StationScheduleEvent();
            event.setDate(LocalDateTime.ofInstant(presence.getInstant(),ZoneOffset.ofHours(presence.getSpot().getStation().getTimezone())).toLocalDate());
            event.setTime(LocalDateTime.ofInstant(presence.getInstant(),ZoneOffset.ofHours(presence.getSpot().getStation().getTimezone())).toLocalTime());
            event.setDatetime(presence.getInstant());
            event.setRoute(presence.getTrain().getRoute().getTitle());
            List<Spot> spots = presence.getTrain().getRoute().getSpots();
            event.setDepartsFrom(spots.get(0).getStation().getTitle());
            event.setArrivesAt(spots.get(spots.size() - 1).getStation().getTitle());
            event.setTrain(presence.getTrain().getId());
            events.add(event);
        });
        return timetable;

        /*StationTimetable timetable = new StationTimetable();

        Collection<StationScheduleEvent> events = new ArrayList<>();
        Station station = stationRepository.findOne(stationId);
        timetable.setTitle(station.getTitle());
        timetable.setScheduleEvents(events);

        Collection<Train> trains = new ArrayList<>();

        station.getSpots().forEach(spot -> {
            Collection<Presence> presences = spot.getPresences();
            presences.forEach(presence -> {
                if (presence.getInstant().isAfter(since.toInstant(ZoneOffset.UTC)) &&
                        presence.getInstant().isBefore(until.toInstant(ZoneOffset.UTC))) {

                    StationScheduleEvent event = new StationScheduleEvent();
                    event.setDate(LocalDateTime.ofInstant(presence.getInstant(), ZoneId.systemDefault()).toLocalDate());
                    event.setTime(LocalDateTime.ofInstant(presence.getInstant(), ZoneId.systemDefault()).toLocalTime());
                    event.setRoute(presence.getTrain().getRoute().getTitle());
                    List<Spot> spots = presence.getTrain().getRoute().getSpots();
                    event.setDepartsFrom(spots.get(0).getStation().getTitle());
                    event.setArrivesAt(spots.get(spots.size() - 1).getStation().getTitle());
                    event.setTrain(presence.getTrain().getId());
                    events.add(event);

                }
            });
        });
        return timetable;*/
    }



}

