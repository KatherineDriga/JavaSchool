package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.*;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final SpotRepository spotRepository;
    private final TicketRepository ticketRepository;
    private final TrainRepository trainRepository;
    private final PresenceRepository presenceRepository;

    @Autowired
    public TrainServiceImpl(RouteRepository routeRepository, StationRepository stationRepository, SpotRepository spotRepository, TicketRepository ticketRepository, TrainRepository trainRepository, PresenceRepository presenceRepositary) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.spotRepository = spotRepository;
        this.ticketRepository = ticketRepository;
        this.trainRepository = trainRepository;
        this.presenceRepository = presenceRepositary;
    }
    @Override
    public Collection<TrainInfo> getAll(int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until) {

        Station stationOfDeparture = stationRepository.findOne(stationOfDepartureId);
        int timezoneDeparture = stationOfDeparture.getTimezone();
        Instant sinceDateTime = since.toInstant(ZoneOffset.ofHours(timezoneDeparture));
        Instant untilDateTime = until.toInstant(ZoneOffset.ofHours(timezoneDeparture));

        Collection<Train> trains = trainRepository.findByDepartureArrivalAndTime(
                stationOfDepartureId, stationOfDepartureId, sinceDateTime, untilDateTime);
        Collection<TrainInfo> trainInfos = new ArrayList<>();
        trains.forEach(train -> {
            TrainInfo trainInfo = new TrainInfo();
            trainInfo.setTrainId(train.getId());
            trainInfo.setRouteTitle(train.getRoute().getTitle());
            trainInfo.setStationOfDeparture(stationRepository.findOne(stationOfDepartureId).getTitle());
            trainInfo.setStationOfArrival(stationRepository.findOne(stationOfArrivalId).getTitle());

            Collection<Presence> presences = train.getPresences();
            boolean isDeparturePassed = false;
            boolean isArrivalNotPassed = true;
            int minutesDeparture = 0;
            int minutesArrival = 0;

            int ticketsAvailable = train.getNumberOfSeats();

            for (Presence presence : presences) {
                if (presence.getSpot().getStation().getId() == stationOfDepartureId) {
                    isDeparturePassed = true;
                    LocalDateTime datetimeDeparture = train.getDateOfDeparture()
                            .atTime(train.getRoute().getTimeOfDeparture())
                            .plus(presence.getSpot().getMinutesSinceDeparture(), ChronoUnit.MINUTES);
                    trainInfo.setDateOfDeparture(datetimeDeparture.toLocalDate());
                    trainInfo.setTimeOfDeparture(datetimeDeparture.toLocalTime());
                    minutesDeparture = presence.getSpot().getMinutesSinceDeparture();
                }
                if (presence.getSpot().getStation().getId() == stationOfArrivalId) {
                    isArrivalNotPassed = false;
                    LocalDateTime datetimeArrival = train.getDateOfDeparture()
                            .atTime(train.getRoute().getTimeOfDeparture())
                            .plus(presence.getSpot().getMinutesSinceDeparture(), ChronoUnit.MINUTES);
                    trainInfo.setDateOfArrival(datetimeArrival.toLocalDate());
                    trainInfo.setTimeOfArrival(datetimeArrival.toLocalTime());
                    minutesArrival = presence.getSpot().getMinutesSinceDeparture();

                }
                if (isDeparturePassed && isArrivalNotPassed) {
                    ticketsAvailable = Math.min(presence.getNumberOfTickets(), ticketsAvailable);
                }
            }
            trainInfo.setNumberOfSeatsAvailable(ticketsAvailable);

            trainInfo.setTicketPrice((new BigDecimal(minutesArrival-minutesDeparture)).multiply(new BigDecimal(train.getPriceCoefficient())).multiply(train.getRoute().getPricePerMinute()));
            if (trainInfo.getTicketPrice().compareTo(BigDecimal.ZERO) > 0){
                trainInfos.add(trainInfo);
            }
        });
        return trainInfos;
        /*return trains.stream().map(train -> {

            TrainInfo trainInfo = new TrainInfo();
            trainInfo.setTrainId(train.getId());
            trainInfo.setRouteTitle(train.getRoute().getTitle());
            trainInfo.setStationOfDeparture(stationRepository.findOne(stationOfDepartureId).getTitle());
            trainInfo.setStationOfArrival(stationRepository.findOne(stationOfArrivalId).getTitle());

            Collection<Presence> presences = train.getPresences();
            boolean isDeparturePassed = false;
            boolean isArrivalNotPassed = true;
            int minutesDeparture = 0;
            int minutesArrival = 0;

            int ticketsAvailable = train.getNumberOfSeats();

            for (Presence presence : presences) {
                if (presence.getSpot().getStation().getId() == stationOfDepartureId) {
                    isDeparturePassed = true;
                    LocalDateTime datetimeDeparture = train.getDateOfDeparture()
                            .atTime(train.getRoute().getTimeOfDeparture())
                            .plus(presence.getSpot().getMinutesSinceDeparture(), ChronoUnit.MINUTES);
                    trainInfo.setDateOfDeparture(datetimeDeparture.toLocalDate());
                    trainInfo.setTimeOfDeparture(datetimeDeparture.toLocalTime());
                    minutesDeparture = presence.getSpot().getMinutesSinceDeparture();
                }
                if (presence.getSpot().getStation().getId() == stationOfArrivalId) {
                    isArrivalNotPassed = false;
                    LocalDateTime datetimeArrival = train.getDateOfDeparture()
                            .atTime(train.getRoute().getTimeOfDeparture())
                            .plus(presence.getSpot().getMinutesSinceDeparture(), ChronoUnit.MINUTES);
                    trainInfo.setDateOfArrival(datetimeArrival.toLocalDate());
                    trainInfo.setTimeOfArrival(datetimeArrival.toLocalTime());
                    minutesArrival = presence.getSpot().getMinutesSinceDeparture();

                }
                if (isDeparturePassed && isArrivalNotPassed) {
                    ticketsAvailable = Math.min(presence.getNumberOfTickets(), ticketsAvailable);
                }
            }
            trainInfo.setNumberOfSeatsAvailable(ticketsAvailable);

            trainInfo.setTicketPrice((new BigDecimal(minutesArrival-minutesDeparture)).multiply(new BigDecimal(train.getPriceCoefficient())).multiply(train.getRoute().getPricePerMinute()));
            if (trainInfo.getTicketPrice().compareTo(BigDecimal.ZERO) < 0){
                return null;
            }
            return trainInfo;
        }).collect(Collectors.toList());*/
    }

    @Override
    public TrainInfo create(int routeId, LocalDate dateOfDeparture, int numberOfSeats, double priceCoefficient) {

        Train findTrain = trainRepository.findByRouteIdAndDateOfDeparture(routeId, dateOfDeparture);
        if (findTrain != null) {
            throw new RuntimeException("Train already exists");
        }

        Train train = new Train();
        Collection<Ticket> tickets = null;
        Route route = routeRepository.findOne(routeId);
        train.setTickets(tickets);
        train.setRoute(route);
        train.setDateOfDeparture(dateOfDeparture);
        train.setNumberOfSeats(numberOfSeats);
        train.setPriceCoefficient(priceCoefficient);
        train.setArchived(false);

        int trainId = trainRepository.save(train).getId();

        Train addedTrain = trainRepository.findOne(trainId);
        Collection<Spot> spots = spotRepository.findByRouteId(routeId);
        spots.forEach(spot -> {
            Presence presence = new Presence();
            presence.setTrain(addedTrain);
            presence.setSpot(spot);
            Instant instant = addedTrain.getDateOfDeparture()
                    .atTime(addedTrain.getRoute().getTimeOfDeparture())
                    .plus(spot.getMinutesSinceDeparture(), ChronoUnit.MINUTES)
                    .toInstant(ZoneOffset.ofHours(spot.getStation().getTimezone()));
            presence.setInstant(instant);
            presence.setNumberOfTickets(0);
            presenceRepository.save(presence);
        });

        TrainInfo trainInfo = new TrainInfo();
        trainInfo.setTrainId(trainId);
        trainInfo.setRouteTitle(routeRepository.findOne(routeId).getTitle());
        trainInfo.setDateOfDeparture(dateOfDeparture);
        trainInfo.setNumberOfSeats(numberOfSeats);

        return trainInfo;
    }

    @Override
    public Collection<TrainInfo> getTrainInfos(int routeId) {

        Collection<TrainInfo> trainInfos = new ArrayList<>();
        Collection<Train> trains = trainRepository.findByRouteId(routeId);
        trains.forEach(train -> {
            if (!train.isArchived()) {
                TrainInfo trainInfo = new TrainInfo();
                trainInfo.setTrainId(train.getId());
                trainInfo.setRouteTitle(train.getRoute().getTitle());
                trainInfo.setDateOfDeparture(train.getDateOfDeparture());
                trainInfos.add(trainInfo);
            }
        });

        return trainInfos;
    }





















    @Override
    public TrainTimetable getTimetable(int stationOfDepartureId,
                                       int stationOfArrivalId,
                                       LocalDateTime since,
                                       LocalDateTime until) {

        TrainTimetable timetable = new TrainTimetable();
        Station stationA = stationRepository.findOne(stationOfDepartureId);
        Station stationB = stationRepository.findOne(stationOfArrivalId);
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
                                && spotA.getMinutesSinceDeparture() < spotB.getMinutesSinceDeparture()) {
                            routeA.getTrains().forEach(train -> {
                                TrainScheduleEvent event = new TrainScheduleEvent();
                                LocalDate dateOfDeparture = train.getDateOfDeparture();
                                LocalTime timeOfDeparture = routeA.getTimeOfDeparture();
                                LocalDateTime datetimeOfDeparture = dateOfDeparture.atTime(timeOfDeparture)
                                        .plus(spotA.getMinutesSinceDeparture(), ChronoUnit.MINUTES);

                                if (datetimeOfDeparture.isBefore(until) &&
                                        (datetimeOfDeparture.isAfter(since) || datetimeOfDeparture.isEqual(since))) {
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

