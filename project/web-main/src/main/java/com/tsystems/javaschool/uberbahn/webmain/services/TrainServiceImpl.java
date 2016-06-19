package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.*;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class TrainServiceImpl extends BaseServiceImpl implements TrainService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final SpotRepository spotRepository;
    private final TicketRepository ticketRepository;
    private final TrainRepository trainRepository;

    public TrainServiceImpl(Session session) {
        super(session);
        this.routeRepository = new RouteRepositoryImpl(session);
        this.stationRepository = new StationRepositoryImpl(session);
        this.spotRepository = new SpotRepositoryImpl(session);
        this.ticketRepository = new TicketRepositoryImpl(session);
        this.trainRepository = new TrainRepositoryImpl(session);
    }

    @Override
    public TrainTimetable getTimetable(int stationOfDepartureId,
                                       int stationOfArrivalId,
                                       LocalDateTime since,
                                       LocalDateTime until) {

        TrainTimetable timetable = new TrainTimetable();
        Station stationA = stationRepository.findById(stationOfDepartureId);
        Station stationB = stationRepository.findById(stationOfArrivalId);
        Map events = new HashMap<Integer, TrainScheduleEvent>();
        timetable.setStationOfDeparture(stationA.getTitle());
        timetable.setStationOfArrival(stationB.getTitle());


        Collection<Route> routesPassStationA = routeRepository.findByStationId(stationA.getId());
        Collection<Route> routesPassStationB = routeRepository.findByStationId(stationA.getId());

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

                                    events.put(train.getId(), event);
                                }

                            });

                        }

                    });
                });

            });
        });
        Collection<TrainScheduleEvent> scheduleEvents = new ArrayList<>();
        scheduleEvents = events.values();
        timetable.setScheduleEvents(scheduleEvents);
        return timetable;
    }

    @Override
    public Collection<TrainInfo> getTrainInfo(int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until) {

        Map trInfos = new HashMap<Integer, TrainInfo>();
        TrainTimetable timetable = getTimetable(stationOfDepartureId, stationOfArrivalId, since, until);
        Collection<TrainScheduleEvent> events = timetable.getScheduleEvents();
        Collection<Route> routes = new ArrayList<>();
        events.forEach(trainScheduleEvent -> {
            String routeTitle = trainScheduleEvent.getRouteTitle();
            Route route = routeRepository.findByTitle(routeTitle);
            routes.add(route);
        });
        routes.forEach(route -> {
            Spot stationOfDep = spotRepository.findByStationIdAndRouteId(stationOfDepartureId, route.getId());
            Spot stationOfArr = spotRepository.findByStationIdAndRouteId(stationOfArrivalId, route.getId());
            Integer minutesSinceDepartureForStationA = stationOfDep.getMinutesSinceDeparture();
            Integer minutesSinceDepartureForStationB = stationOfArr.getMinutesSinceDeparture();
            //Collection<Spot> spots = spotRepository.findAllBetweenStationsByRouteIdAndTime(route.getId(), minutesSinceDepartureForStationA, minutesSinceDepartureForStationB);
            Collection<Train> trains = route.getTrains();
            trains.forEach(train -> {
                TrainInfo info = new TrainInfo();
                Collection<Spot> spots = route.getSpots();
                Map stationsNumberOfPurchasedSeats = new HashMap<Integer, Integer>();
                spots.forEach(spot -> {
                    stationsNumberOfPurchasedSeats.put(spot.getStation().getId(), 0);
                });
                Collection<Ticket> tickets = train.getTickets();
                Collection<TicketsPurchasedPerStation> ticketsPurchasedPerStations = new ArrayList<>();
                tickets.forEach(ticket -> {
                    int minutesSinceDepartureDep = spotRepository.findByStationIdAndRouteId(ticket.getStationOfDeparture().getId(), route.getId()).getMinutesSinceDeparture();
                    int minutesSinceDepartureArr = spotRepository.findByStationIdAndRouteId(ticket.getStationOfArrival().getId(), route.getId()).getMinutesSinceDeparture();
                    Collection<Spot> spotsBetweenStations = spotRepository.findAllBetweenStationsByRouteIdAndTime(route.getId(), minutesSinceDepartureDep, minutesSinceDepartureArr);
                    spotsBetweenStations.forEach(spot -> {
                            int numberOfSeats = (int) stationsNumberOfPurchasedSeats.get(spot.getStation().getId());
                            numberOfSeats++;
                            stationsNumberOfPurchasedSeats.put(spot.getStation().getId(), numberOfSeats);
                    });
                });
                info.setTrainId(train.getId());
                info.setRouteTitle(route.getTitle());
                info.setStationOfDeparture(stationRepository.findById(stationOfDepartureId).getTitle());

                LocalDate dateOfDeparture = train.getDateOfDeparture();
                LocalTime timeOfDeparture = route.getTimeOfDeparture();
                LocalDateTime datetimeOfDeparture = dateOfDeparture.atTime(timeOfDeparture)
                        .plus(minutesSinceDepartureForStationA, ChronoUnit.MINUTES);
                LocalDateTime datetimeOfArrival = dateOfDeparture.atTime(timeOfDeparture)
                        .plus(minutesSinceDepartureForStationB, ChronoUnit.MINUTES);

                info.setDateOfDeparture(datetimeOfDeparture.toLocalDate());
                info.setTimeOfDeparture(datetimeOfDeparture.toLocalTime());
                info.setStationOfArrival(stationRepository.findById(stationOfArrivalId).getTitle());
                info.setDateOfArrival(datetimeOfArrival.toLocalDate());
                info.setTimeOfArrival(datetimeOfArrival.toLocalTime());

                Collection<Spot> spotsBetweenSearch = spotRepository.findAllBetweenStationsByRouteIdAndTime(route.getId(), minutesSinceDepartureForStationA, minutesSinceDepartureForStationB);
                int ticketsPurchased = 0;
                for (Spot spot : spotsBetweenSearch){
                    int purchasedSeats = (int) stationsNumberOfPurchasedSeats.get(spot.getStation().getId());
                    if (purchasedSeats > ticketsPurchased){
                        ticketsPurchased = purchasedSeats;
                    }
                }
                int numberOfSeatsAvailable = train.getNumberOfSeats() - ticketsPurchased;

                info.setNumberOfSeatsAvailable(numberOfSeatsAvailable);

                trInfos.put(train.getId(), info);

                });
            });
        Collection<TrainInfo> trainInfos = new ArrayList<>();
        trainInfos = trInfos.values();
        return trainInfos;
    }

    @Override
    public AddTrainInfo getAddTrainInfo(int routeId, LocalDate dateOfDeparture, int numberOfSeats) {
        AddTrainInfo addTrainInfo = new AddTrainInfo();
        Train findTrain = trainRepository.findByRouteIdAndDateOfDeparture(routeId, dateOfDeparture);
        if (findTrain != null){
            throw new RuntimeException("Train already exists");
        }

            Train train = new Train();
            Collection<Ticket> tickets = null;
            Route route = routeRepository.findById(routeId);
            train.setTickets(tickets);
            train.setRoute(route);
            train.setDateOfDeparture(dateOfDeparture);
            train.setNumberOfSeats(numberOfSeats);

            int trainId = trainRepository.save(train).getId();

            if (trainId != 0){
                addTrainInfo.setId(trainId);
                addTrainInfo.setRouteId(routeId);
                addTrainInfo.setDateOfDeparture(dateOfDeparture);
                addTrainInfo.setNumberOfSeats(numberOfSeats);
                addTrainInfo.setMessage("Train " + trainId + " is added");

            }


        return addTrainInfo;
    }

    @Override
    public Collection<FindTrainInfo> getFindTrainInfo(int routeId) {
        Collection<FindTrainInfo> findTrainInfos = new ArrayList<>();
        Collection<Train> trains = trainRepository.findByRouteId(routeId);
        trains.forEach(train -> {
            FindTrainInfo findTrainInfo = new FindTrainInfo();
            findTrainInfo.setId(train.getId());
            findTrainInfo.setRouteTitle(train.getRoute().getTitle());
            findTrainInfo.setDateOfDeparture(train.getDateOfDeparture());
            findTrainInfos.add(findTrainInfo);
        });

        return findTrainInfos;
    }

    @Override
    public Collection<PassengerInfo> getPassengerInfo(int trainId) {
        Collection<PassengerInfo> passengerInfos = new ArrayList<>();
        Collection<Ticket> tickets = trainRepository.getTicketsByTrainId(trainId);
        tickets.forEach(ticket -> {
            PassengerInfo passengerInfo = new PassengerInfo();
            passengerInfo.setFirstName(ticket.getFirstName());
            passengerInfo.setLastName(ticket.getLastName());
            passengerInfo.setDateOfBirth(ticket.getDateOfBirth());
            passengerInfo.setStationOfDeparture(ticket.getStationOfDeparture().getTitle());
            passengerInfo.setStationOfArrival(ticket.getStationOfArrival().getTitle());
            passengerInfos.add(passengerInfo);
        });

        return passengerInfos;
    }
}

