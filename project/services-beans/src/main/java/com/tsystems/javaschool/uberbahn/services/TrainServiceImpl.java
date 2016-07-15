package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.entities.*;
import com.tsystems.javaschool.uberbahn.repositories.*;
import com.tsystems.javaschool.uberbahn.services.TrainService;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.services.errors.DatabaseException;
import com.tsystems.javaschool.uberbahn.transports.PassengerInfo;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final SpotRepository spotRepository;
    private final TrainRepository trainRepository;
    private final PresenceRepository presenceRepository;

    @Autowired
    public TrainServiceImpl(RouteRepository routeRepository, StationRepository stationRepository, SpotRepository spotRepository, TrainRepository trainRepository, PresenceRepository presenceRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.spotRepository = spotRepository;
        this.trainRepository = trainRepository;
        this.presenceRepository = presenceRepository;
    }


    @Override
    public Collection<TrainInfo> getAll(int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until) {
        checkFields(stationOfDepartureId, stationOfArrivalId, since, until);
        Station stationOfDeparture = stationRepository.findOne(stationOfDepartureId);
        Station stationOfArrival = stationRepository.findOne(stationOfArrivalId);
        Instant sinceDateTime = since.toInstant(ZoneOffset.ofHours(stationOfDeparture.getTimezone()));
        Instant untilDateTime = until.toInstant(ZoneOffset.ofHours(stationOfDeparture.getTimezone()));

        Collection<Presence> presences = presenceRepository.findByDepartureArrivalStationAndTime(stationOfDepartureId, stationOfArrivalId, sinceDateTime, untilDateTime);
        Collection<Train> trains = presences.stream().map(presence -> {
            return presence.getTrain();
        }).collect(Collectors.toList());

        return trains.stream().map(train -> {
            TrainInfo trainInfo = new TrainInfo();
            trainInfo.setTrainId(train.getId());
            trainInfo.setRouteTitle(train.getRoute().getTitle());
            trainInfo.setStationOfDeparture(stationOfDeparture.getTitle());
            trainInfo.setStationOfArrival(stationOfArrival.getTitle());

            Collection<Presence> presencesPassed = presenceRepository.findByTrainId(train.getId());
            boolean isDeparturePassed = false;
            boolean isArrivalNotPassed = true;
            int minutesDeparture = 0;
            int minutesArrival = 0;

            int ticketsAvailable = train.getNumberOfSeats();

            for (Presence presence : presencesPassed) {
                if (presence.getSpot().getStation().getId() == stationOfDepartureId) {
                    isDeparturePassed = true;

                    OffsetDateTime datetimeDeparture = presence.getInstant().atOffset(ZoneOffset.ofHours(stationOfDeparture.getTimezone()));
                    trainInfo.setDateOfDeparture(datetimeDeparture.toLocalDate());
                    trainInfo.setTimeOfDeparture(datetimeDeparture.toLocalTime());
                    minutesDeparture = presence.getSpot().getMinutesSinceDeparture();
                }
                if (presence.getSpot().getStation().getId() == stationOfArrivalId) {
                    isArrivalNotPassed = false;
                    OffsetDateTime datetimeArrival = presence.getInstant().atOffset(ZoneOffset.ofHours(stationOfArrival.getTimezone()));

                    trainInfo.setDateOfArrival(datetimeArrival.toLocalDate());
                    trainInfo.setTimeOfArrival(datetimeArrival.toLocalTime());
                    minutesArrival = presence.getSpot().getMinutesSinceDeparture();

                }
                if (isDeparturePassed && isArrivalNotPassed) {
                    ticketsAvailable = Math.min((train.getNumberOfSeats()-presence.getNumberOfTicketsPurchased()), ticketsAvailable);
                }
            }
            trainInfo.setNumberOfSeatsAvailable(ticketsAvailable);
            int duration = minutesArrival-minutesDeparture;
            trainInfo.setTravelTime(countTravelTime(duration));
            trainInfo.setTicketPrice(((new BigDecimal(duration)).multiply(new BigDecimal(train.getPriceCoefficient())).multiply(train.getRoute().getPricePerMinute())).setScale(2, BigDecimal.ROUND_HALF_UP));
            return trainInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public TrainInfo create(int routeId, LocalDate dateOfDeparture, int numberOfSeats, double priceCoefficient) {

        checkFieldsToCreate(routeId, dateOfDeparture, numberOfSeats, priceCoefficient);
        Train train = new Train();
        Route route = routeRepository.findOne(routeId);
        train.setRoute(route);
        train.setDateOfDeparture(dateOfDeparture);
        train.setNumberOfSeats(numberOfSeats);
        train.setPriceCoefficient(priceCoefficient);
        train.setArchived(false);
        try {
            trainRepository.save(train);
        } catch (PersistenceException | NullPointerException ex) {
            throw new DatabaseException("Error occurred", ex);
        }

        saveSpots(train);

        TrainInfo trainInfo = new TrainInfo();
        trainInfo.setTrainId(train.getId());
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
    public Collection<PassengerInfo> getPassengerInfo(int trainId) {
        return trainRepository.getTicketsByTrainId(trainId).stream().map(ticket -> {
            PassengerInfo passengerInfo = new PassengerInfo();
            passengerInfo.setFirstName(ticket.getFirstName());
            passengerInfo.setLastName(ticket.getLastName());
            passengerInfo.setDateOfBirth(ticket.getDateOfBirth());
            passengerInfo.setStationOfDeparture(ticket.getStationOfDeparture().getTitle());
            passengerInfo.setStationOfArrival(ticket.getStationOfArrival().getTitle());
            return passengerInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean existsTrain(int routeId, LocalDate dateOfDeparture) {
        if (trainRepository.findByRouteIdAndDateOfDeparture(routeId, dateOfDeparture) != null) {
            return true;
        }
        return false;
    }

    @Override
    public TrainInfo getByDepartureArrivalAndTrainId(int stationOfDepartureId, int stationOfArrivalId, int trainId) {
        TrainInfo trainInfo = new TrainInfo();
        Train train = trainRepository.findOne(trainId);
        Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(trainId, stationOfDepartureId);
        Presence presenceArrival = presenceRepository.findByTrainIdAndStationId(trainId, stationOfArrivalId);
        trainInfo.setRouteTitle(train.getRoute().getTitle());
        OffsetDateTime datetimeDeparture = presenceDeparture.getInstant().atOffset(ZoneOffset.ofHours(stationRepository.findOne(stationOfDepartureId).getTimezone()));
        OffsetDateTime datetimeArrival = presenceArrival.getInstant().atOffset(ZoneOffset.ofHours(stationRepository.findOne(stationOfArrivalId).getTimezone()));
        trainInfo.setDateOfDeparture(datetimeDeparture.toLocalDate());
        trainInfo.setTimeOfDeparture(datetimeDeparture.toLocalTime());
        trainInfo.setStationOfDeparture(stationRepository.findOne(stationOfDepartureId).getTitle());
        trainInfo.setDateOfArrival(datetimeArrival.toLocalDate());
        trainInfo.setTimeOfArrival(datetimeArrival.toLocalTime());
        trainInfo.setStationOfArrival(stationRepository.findOne(stationOfArrivalId).getTitle());

        return trainInfo;
    }

    private String countTravelTime (int duration){
        long days = TimeUnit.MINUTES.toDays(duration);
        long hours = TimeUnit.MINUTES.toHours(duration) - days*24;
        long minutes = duration - days*24*60 - hours*60;
        return days + "d " + hours + "h "+ minutes + "m";
    }

    private void checkFields(int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until) {
        if (stationOfDepartureId == stationOfArrivalId) {
            throw new BusinessLogicException("Stations Stations of departure and arrival should be different");
        }
        if (since.isAfter(until)) {
            throw new BusinessLogicException("Invalid dates");
        }
    }

    private void checkFieldsToCreate(int routeId, LocalDate dateOfDeparture, int numberOfSeats, double priceCoefficient) {
        if (dateOfDeparture == null) {
            throw new BusinessLogicException("Enter date of departure");
        }
        if (numberOfSeats <= 0) {
            throw new BusinessLogicException("Invalid number of Seats");
        }
        if (priceCoefficient <= 0) {
            throw new BusinessLogicException("Invalid price coefficient");
        }
        if (existsTrain(routeId, dateOfDeparture)) {
            throw new BusinessLogicException(String.format("Train %s already exists", dateOfDeparture));
        }
        if (routeRepository.findOne(routeId) == null) {
            throw new BusinessLogicException("Route required");
        }
    }

    private void saveSpots (Train train) {
        Collection<Spot> spots = spotRepository.findByRouteId(train.getRoute().getId());
        Instant datetimeDeparture = train.getDateOfDeparture()
                .atTime(train.getRoute().getTimeOfDeparture())
                .toInstant(ZoneOffset.ofHours(train.getRoute().getSpots().get(0).getStation().getTimezone()));
        spots.forEach(spot -> {
            Presence presence = new Presence();
            presence.setTrain(train);
            presence.setSpot(spot);
            Instant instant = datetimeDeparture
                    .plus(spot.getMinutesSinceDeparture(), ChronoUnit.MINUTES);
            presence.setInstant(instant);
            presence.setNumberOfTicketsPurchased(0);
            try {
                presenceRepository.save(presence);
            } catch (PersistenceException | NullPointerException ex) {
                throw new DatabaseException("Error occurred", ex);
            }
        });
    }

}

