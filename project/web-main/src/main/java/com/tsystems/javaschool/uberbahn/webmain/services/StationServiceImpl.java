package com.tsystems.javaschool.uberbahn.webmain.services;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;
import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import com.tsystems.javaschool.uberbahn.webmain.entities.Train;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationTimetable;
import org.hibernate.Session;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class StationServiceImpl extends BaseServiceImpl implements StationService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;


    public StationServiceImpl(Session session) {
        super(session);
        this.routeRepository = new RouteRepositoryImpl(session);
        this.stationRepository = new StationRepositoryImpl(session);
    }


    @Override
    public StationTimetable getTimetable(int stationId, LocalDateTime since, LocalDateTime until) {

        StationTimetable timetable = new StationTimetable();

        Collection<StationScheduleEvent> events = new ArrayList<>();
        Station station = stationRepository.findById(stationId);
        timetable.setTitle(station.getTitle());
        timetable.setScheduleEvents(events);

        station.getSpots().forEach(spot -> {
            Route route = spot.getRoute();
            route.getTrains().forEach(train -> {

                LocalDate dateOfDeparture = train.getDateOfDeparture();
                LocalTime timeOfDeparture = route.getTimeOfDeparture();
                LocalDateTime datetime = dateOfDeparture.atTime(timeOfDeparture);
                datetime.plus(spot.getMinutesSinceDeparture(), ChronoUnit.MINUTES);

                if (datetime.isBefore(until) &&
                        (datetime.isAfter(since) || datetime.isEqual(since))){

                    List<Spot> spots =  route.getSpots();
                    String departsFrom = spots.get(0).getStation().getTitle();
                    String arrivesAt = spots.get(spots.size()-1).getStation().getTitle();

                    StationScheduleEvent event = new StationScheduleEvent();
                    event.setDate(datetime.toLocalDate());
                    event.setTime(datetime.toLocalTime());
                    event.setRoute(route.getTitle());
                    event.setDepartsFrom(departsFrom);
                    event.setArrivesAt(arrivesAt);
                    event.setTrain(train.getId());
                    events.add(event);
                }
            });
        });
        return timetable;
    }
}

