package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.*;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketInfo;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
        this.stationRepository = new StationRepositoryImpl(session);
        this.accountRepository = new AccountRepositoryImpl(session);
        this.ticketRepository = new TicketRepositoryImpl(session);
        this.routeRepository = new RouteRepositoryImpl(session);
        this.spotRepository = new SpotRepositoryImpl(session);
    }

    @Override
    public TicketInfo getTicketInfo(int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, int accountId) {
        TicketInfo ticketInfo = new TicketInfo();
        Collection<Ticket> tickets = trainRepository.getTicketsByTrainId(trainId);
        Train train = trainRepository.findById(trainId);
        Station stationOfDeparture = stationRepository.findById(stationOfDepartureId);
        Station stationOfArrival = stationRepository.findById(stationOfArrivalId);
        LocalDateTime datetimeOfPurchase = LocalDateTime.now();
        Account account = accountRepository.findById(accountId);
        Route route = routeRepository.findById(train.getRoute().getId());
        Spot spotDeparture = spotRepository.findByStationIdAndRouteId(stationOfDepartureId, route.getId());
        Spot spotArrival = spotRepository.findByStationIdAndRouteId(stationOfDepartureId, route.getId());
        LocalDate dateOfDeparture = train.getDateOfDeparture();
        LocalTime timeOfDeparture = route.getTimeOfDeparture();
        LocalDateTime datetimeOfDeparture = dateOfDeparture.atTime(timeOfDeparture)
                .plus(spotDeparture.getMinutesSinceDeparture(), ChronoUnit.MINUTES);
        LocalDateTime datetimeOfArrival = dateOfDeparture.atTime(timeOfDeparture)
                .plus(spotArrival.getMinutesSinceDeparture(), ChronoUnit.MINUTES);

        for (Ticket ticket : tickets) {
            if (ticket.getFirstName().equals(firstName) && ticket.getLastName().equals(lastName) && ticket.getDateOfBirth().isEqual(dateOfBirth)) {
                ticketInfo.setMessage("Passenger is already registered");
                return ticketInfo;
            }
        }

        if (ChronoUnit.MINUTES.between(datetimeOfPurchase, datetimeOfDeparture) < 10){
            ticketInfo.setMessage("Less than 10 minutes before departure");
            return ticketInfo;
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

        int ticketId = ticketRepository.save(ticket).getId();

        if (ticketId != 0){
            ticketInfo.setId(ticketId);
            ticketInfo.setTrainId(trainId);
            ticketInfo.setFirstName(firstName);
            ticketInfo.setLastName(lastName);
            ticketInfo.setDateOfBirth(dateOfBirth);
            ticketInfo.setStationOfDeparture(stationOfDeparture.getTitle());
            ticketInfo.setStationOfArrival(stationOfArrival.getTitle());
            ticketInfo.setDatetimeOfDeparture(datetimeOfDeparture);
            ticketInfo.setDatetimeOfArrival(datetimeOfArrival);
            ticketInfo.setDatetimeOfPurchase(datetimeOfPurchase);
            ticketInfo.setMessage("You've successfully purchased ticket number " + ticketId);

            return ticketInfo;
        }
        else {
            ticketInfo.setMessage("No seats available");
            return ticketInfo;
        }

    }
}
