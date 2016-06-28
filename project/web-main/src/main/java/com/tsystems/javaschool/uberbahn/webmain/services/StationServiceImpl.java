package com.tsystems.javaschool.uberbahn.webmain.services;

import com.tsystems.javaschool.uberbahn.webmain.entities.*;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationInfo;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationTimetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(RouteRepository routeRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
    }


    @Override
    public StationTimetable getTimetable(int stationId, LocalDateTime since, LocalDateTime until) {

        StationTimetable timetable = new StationTimetable();

        Collection<StationScheduleEvent> events = new ArrayList<>();
        Station station = stationRepository.findOne(stationId);
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
    public StationInfo getStationInfo(String stationTitle) {
        StationInfo stationInfo = new StationInfo();
        /*Station findStation = stationRepository.findByTitle(stationTitle);

        if (findStation != null){
            throw new RuntimeException("Station already exists");
        }
        else {*/
            Station station = new Station();
            Collection<Spot> spots = null;
            Collection<Ticket> departures = null;
            Collection<Ticket> arrivals = null;
            station.setTitle(stationTitle);
            station.setSpots(spots);
            station.setDepartures(departures);
            station.setArrivals(arrivals);

            int stationId = stationRepository.save(station).getId();

            if (stationId != 0){
                stationInfo.setId(stationId);
                stationInfo.setTitle(stationTitle);
                stationInfo.setMessage("Station " + stationTitle + " is added");

            }

        return stationInfo;
    }

}

