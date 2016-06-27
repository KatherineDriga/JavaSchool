package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.*;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketInfo;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketsPurchasedPerStation;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

public class TicketServiceImpl extends BaseServiceImpl implements TicketService {

    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;
    private final RouteRepository routeRepository;
    private final SpotRepository spotRepository;

    public TicketServiceImpl(Session session) {
        super(session);
        this.trainRepository = new TrainRepositoryImpl(session);
        this.stationRepository = null;
        this.accountRepository = new AccountRepositoryImpl(session);
        this.ticketRepository = new TicketRepositoryImpl(session);
        this.routeRepository = null;
        this.spotRepository = new SpotRepositoryImpl(session);
    }

    @Override
    public TicketInfo getTicketInfo(int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, int accountId) {
        TicketInfo ticketInfo = new TicketInfo();

        Train train = trainRepository.findById(trainId);
        Route route = routeRepository.findOne(train.getRoute().getId());
        Spot stationOfDep = spotRepository.findByStationIdAndRouteId(stationOfDepartureId, route.getId());
        Spot stationOfArr = spotRepository.findByStationIdAndRouteId(stationOfArrivalId, route.getId());
        Integer minutesSinceDepartureForStationA = stationOfDep.getMinutesSinceDeparture();
        Integer minutesSinceDepartureForStationB = stationOfArr.getMinutesSinceDeparture();
        Collection<Spot> spots = null; //spotRepository.findAllBetweenStationsByRouteIdAndTime(route.getId(), minutesSinceDepartureForStationA, minutesSinceDepartureForStationB);
        spots.forEach(spot -> {
            Collection<Ticket> tickets = ticketRepository.getByTrainIdAndStationOfDeparture(train.getId(), spot.getStation().getId());
            int ticketsPurchasedPerStation = tickets.size();
            if (ticketsPurchasedPerStation == train.getNumberOfSeats()){
                throw new RuntimeException("No tickets available");
            }
        });

        Station stationOfDeparture = stationRepository.findOne(stationOfDepartureId);
        Station stationOfArrival = stationRepository.findOne(stationOfArrivalId);
        LocalDateTime datetimeOfPurchase = LocalDateTime.now();
        Account account = accountRepository.findById(accountId);
        Spot spotDeparture = spotRepository.findByStationIdAndRouteId(stationOfDepartureId, route.getId());
        Spot spotArrival = spotRepository.findByStationIdAndRouteId(stationOfArrivalId, route.getId());
        LocalDate dateOfDeparture = train.getDateOfDeparture();
        LocalTime timeOfDeparture = route.getTimeOfDeparture();
        LocalDateTime datetimeOfDeparture = dateOfDeparture.atTime(timeOfDeparture)
                .plus(spotDeparture.getMinutesSinceDeparture(), ChronoUnit.MINUTES);
        LocalDateTime datetimeOfArrival = dateOfDeparture.atTime(timeOfDeparture)
                .plus(spotArrival.getMinutesSinceDeparture(), ChronoUnit.MINUTES);
/*
        for (Ticket ticket : tickets) {
            if (ticket.getFirstName().equals(firstName) && ticket.getLastName().equals(lastName) && ticket.getDateOfBirth().isEqual(dateOfBirth)) {
                ticketInfo.setMessage("Passenger is already registered");
                return ticketInfo;
            }
        }
*/
        if (ChronoUnit.MINUTES.between(datetimeOfPurchase, datetimeOfDeparture) < 10) {
            throw new RuntimeException("Less than 10 minutes before departure");
        }

        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setDateOfBirth(dateOfBirth);
        ticket.setStationOfDeparture(stationOfDeparture);
        ticket.setStationOfArrival(stationOfArrival);
        ticket.setDatetimeOfPurchase(datetimeOfPurchase);
        ticket.setAccount(account);

        ticketRepository.save(ticket);

        ticketInfo.setId(ticket.getId());
        ticketInfo.setTrainId(trainId);
        ticketInfo.setFirstName(firstName);
        ticketInfo.setLastName(lastName);
        ticketInfo.setDateOfBirth(dateOfBirth);
        ticketInfo.setStationOfDeparture(stationOfDeparture.getTitle());
        ticketInfo.setStationOfArrival(stationOfArrival.getTitle());
        ticketInfo.setDatetimeOfDeparture(datetimeOfDeparture);
        ticketInfo.setDatetimeOfArrival(datetimeOfArrival);
        ticketInfo.setDatetimeOfPurchase(datetimeOfPurchase);
        return ticketInfo;

    }

    @Override
    public TicketInfo getTicketInfoByTicketId(int ticketId) {
        TicketInfo ticketInfo = new TicketInfo();
        Ticket ticket = ticketRepository.findById(ticketId);
        Train train = ticket.getTrain();
        ticketInfo.setId(ticketId);
        ticketInfo.setTrainId(ticket.getTrain().getId());
        ticketInfo.setFirstName(ticket.getFirstName());
        ticketInfo.setLastName(ticket.getLastName());
        ticketInfo.setDateOfBirth(ticket.getDateOfBirth());
        ticketInfo.setStationOfDeparture(ticket.getStationOfDeparture().getTitle());
        ticketInfo.setStationOfArrival(ticket.getStationOfArrival().getTitle());

        LocalDate dateOfDeparture = train.getDateOfDeparture();
        LocalTime timeOfDeparture = train.getRoute().getTimeOfDeparture();
        Spot spotDeparture = spotRepository.findByStationIdAndRouteId(ticket.getStationOfDeparture().getId(), train.getRoute().getId());
        Spot spotArrival = spotRepository.findByStationIdAndRouteId(ticket.getStationOfArrival().getId(), train.getRoute().getId());
        LocalDateTime datetimeOfDeparture = dateOfDeparture.atTime(timeOfDeparture)
                .plus(spotDeparture.getMinutesSinceDeparture(), ChronoUnit.MINUTES);
        LocalDateTime datetimeOfArrival = dateOfDeparture.atTime(timeOfDeparture)
                .plus(spotArrival.getMinutesSinceDeparture(), ChronoUnit.MINUTES);

        ticketInfo.setDatetimeOfDeparture(datetimeOfDeparture);
        ticketInfo.setDatetimeOfArrival(datetimeOfArrival);
        ticketInfo.setDatetimeOfPurchase(ticket.getDatetimeOfPurchase());

        return  ticketInfo;
    }
}
