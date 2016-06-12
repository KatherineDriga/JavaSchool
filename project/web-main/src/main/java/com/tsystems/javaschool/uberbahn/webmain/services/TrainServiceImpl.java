package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import com.tsystems.javaschool.uberbahn.webmain.repositories.RouteRepository;
import com.tsystems.javaschool.uberbahn.webmain.repositories.RouteRepositoryImpl;
import com.tsystems.javaschool.uberbahn.webmain.repositories.StationRepository;
import com.tsystems.javaschool.uberbahn.webmain.repositories.StationRepositoryImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.TrainScheduleEvent;
import com.tsystems.javaschool.uberbahn.webmain.transports.TrainTimetable;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;


public class TrainServiceImpl extends BaseServiceImpl implements TrainService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;

    public TrainServiceImpl(Session session) {
        super(session);
        this.routeRepository = new RouteRepositoryImpl(session);
        this.stationRepository = new StationRepositoryImpl(session);
    }

    @Override
    public TrainTimetable getTimetable(String stationOfDeparture,
                                       String stationOfArrival,
                                       LocalDateTime since,
                                       LocalDateTime until) {

        TrainTimetable timetable = new TrainTimetable();
        Station stationA = stationRepository.findByTitle(stationOfDeparture);
        Station stationB = stationRepository.findByTitle(stationOfArrival);
        Collection<TrainScheduleEvent> events = new ArrayList<>();
        timetable.setStationOfDeparture(stationOfDeparture);
        timetable.setStationOfArrival(stationOfArrival);
        timetable.setScheduleEvents(events);

        Collection<Route> routesPassStationA = routeRepository.findByStationId(stationA.getId());
        Collection<Route> routesPassStationB = routeRepository.findByStationId(stationA.getId());
        Collection<Route> routesPassStationAAndB = null;

        routesPassStationA.forEach(routeA -> {
            routesPassStationB.forEach(routeB -> {
                routeA.getSpots().forEach(spotA -> {
                    routeB.getSpots().forEach(spotB -> {
                        if (spotA.getRoute().getId() == spotB.getRoute().getId()
                                && spotA.getStation().getId() == stationA.getId()
                                && spotB.getStation().getId() == stationB.getId()
                                && spotA.getMinutesSinceDeparture() < spotB.getMinutesSinceDeparture()){
                            routeA.getTrains().forEach(train -> {
                                TrainScheduleEvent event = new TrainScheduleEvent();
                                LocalDate dateOfDeparture = train.getDateOfDeparture();
                                LocalTime timeOfDeparture = routeA.getTimeOfDeparture();
                                LocalDateTime datetimeOfDeparture = dateOfDeparture.atTime(timeOfDeparture)
                                        .plus(spotA.getMinutesSinceDeparture(), ChronoUnit.MINUTES);

                                if (datetimeOfDeparture.isBefore(until) &&
                                        (datetimeOfDeparture.isAfter(since) || datetimeOfDeparture.isEqual(since))){
                                    LocalDateTime datetimeOfArrival = dateOfDeparture.atTime(timeOfDeparture)
                                            .plus(spotB.getMinutesSinceDeparture(), ChronoUnit.MINUTES);
                                    event.setTrainId(train.getId());
                                    event.setRouteTitle(routeA.getTitle());
                                    event.setDateOfDeparture(datetimeOfDeparture.toLocalDate());
                                    event.setTimeOfDeparture(datetimeOfDeparture.toLocalTime());
                                    event.setDateOfArrival(datetimeOfArrival.toLocalDate());
                                    event.setTimeOfArrival(datetimeOfArrival.toLocalTime());

                                    events.add(event);
                                }

                            });

                        }

                    });
                });

            });
        });

        return timetable;
    }
}
