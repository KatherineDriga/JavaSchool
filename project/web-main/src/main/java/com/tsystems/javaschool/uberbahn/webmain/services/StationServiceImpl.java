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


public class StationServiceImpl extends BaseServiceImpl implements StationService{

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;


    public StationServiceImpl(Session session) {
        super(session);
        this.routeRepository = new RouteRepositoryImpl(session);
        this.stationRepository = new StationRepositoryImpl(session);
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
/*




 */
