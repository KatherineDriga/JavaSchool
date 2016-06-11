package com.tsystems.javaschool.uberbahn.webmain.services;

import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;
import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import com.tsystems.javaschool.uberbahn.webmain.entities.Train;
import com.tsystems.javaschool.uberbahn.webmain.repositories.TrainRepository;
import com.tsystems.javaschool.uberbahn.webmain.repositories.TrainRepositoryImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.StationScheduleEvent;
import org.hibernate.Session;

import java.time.*;
import java.util.ArrayList;
import java.util.Collection;


public class StationServiceImpl extends BaseServiceImpl implements StationService{


    public StationServiceImpl(Session session) {
        super(session);
    }

    @Override
    public Collection<StationScheduleEvent> getScheduleEvents(int stationId,
                                                              LocalDateTime sinceDateTime,
                                                              LocalDateTime untilDateTime) {
        TrainRepository repository = new TrainRepositoryImpl(getSession()); // TODO: with DI
        Collection<StationScheduleEvent> result = new ArrayList<>();
        /*Collection<Train> trains = repository.findByStation(stationId);


        for (Train train : trains) {

            StationScheduleEvent item = new StationScheduleEvent();
            Collection<Spot> spots = train.getRoute().getSpots();
            int routeId = train.getRoute().getId();
            LocalTime timeSinceDeparture = null;
            LocalDateTime timeOfDepartureOnStation = null;

            for (Spot spot : spots){
                if (spot.getRoute().getId() == routeId && spot.getStation().getId() == stationId){
                    timeSinceDeparture = spot.getMinutesSinceDeparture();
                    break;
                }
            }

            //timeSinceDeparture = repository.findTimeSinceDepartureByStationIdAndRouteId(stationId, routeId);

            timeOfDepartureOnStation = train.getDateOfDeparture().atStartOfDay()
                    .plusSeconds(train.getRoute().getTimeOfDeparture().getSecond())
                    .plusSeconds(timeSinceDeparture.getSecond());


            if (timeOfDepartureOnStation.isBefore(sinceDateTime)
                    || timeOfDepartureOnStation.isAfter(untilDateTime)) continue;

            //Station departureStation = repository.findDepartsFromStaionByRoute(routeId);
            //Station arrivalStation = repository.findArrivesAtStaionByRoute(routeId);

            LocalTime minTime = LocalTime.of(0,0,0);
            LocalTime maxTime = LocalTime.of(0,0,0);
            Station departureStation = null;
            Station arrivalStation = null;
            for (Spot spot : spots){
                LocalTime time = spot.getMinutesSinceDeparture();
                if (time.equals(minTime)){
                    minTime = time;
                    departureStation = spot.getStation();
                }
                else if (time.isAfter(maxTime)){
                    maxTime = time;
                    arrivalStation = spot.getStation();
                }
            }

            item.setDate(LocalDate.of(2016,02,02));
            item.setTime(LocalTime.of(10,0));
            item.setRoute(train.getRoute().getTitle());
            item.setDepartsFrom(departureStation.getTitle());
            item.setArrivesAt(arrivalStation.getTitle());
            item.setTrain(train.getId());
            result.add(item);
        }

        for (StationScheduleEvent res : result){
            System.out.println(res.getDate());
            System.out.println(res.getTime());
            System.out.println(res.getRoute());
            System.out.println(res.getDepartsFrom());
            System.out.println(res.getArrivesAt());
            System.out.println(res.getTrain());
            System.out.println("===========");
        } */

        return result;
    }
}

