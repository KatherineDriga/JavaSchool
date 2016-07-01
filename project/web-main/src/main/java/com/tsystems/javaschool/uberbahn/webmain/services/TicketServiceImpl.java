package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.*;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketInfo;
import com.tsystems.javaschool.uberbahn.webmain.transports.TicketsPurchasedPerStation;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;
    private final RouteRepository routeRepository;
    private final SpotRepository spotRepository;
    private final PresenceRepository presenceRepository;

    @Autowired
    public TicketServiceImpl(TrainRepository trainRepository, StationRepository stationRepository, AccountRepository accountRepository, TicketRepository ticketRepository, RouteRepository routeRepository, SpotRepository spotRepository, PresenceRepository presenceRepository) {
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
        this.accountRepository = accountRepository;
        this.ticketRepository = ticketRepository;
        this.routeRepository = routeRepository;
        this.spotRepository = spotRepository;
        this.presenceRepository = presenceRepository;
    }

    @Override
    public TicketInfo create(int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, int accountId) {

        Train train = trainRepository.findOne(trainId);
        Collection<Ticket> tickets = train.getTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getFirstName().equals(firstName) && ticket.getLastName().equals(lastName) && ticket.getDateOfBirth().isEqual(dateOfBirth)) {
               throw new RuntimeException("Passenger is already registered");
            }
        }
        Collection<Presence> presences = presenceRepository.findByTrainId(trainId);
        int ticketsAvailable = train.getNumberOfSeats();
        boolean isDeparturePassed = false;
        boolean isArrivalNotPassed = true;
        int minutesDeparture = 0;
        int minutesArrival = 0;
        LocalDateTime datetimeDeparture = null;
        LocalDateTime datetimeArrival = null;
        Collection<Presence> presencesPassed = new ArrayList<>();
        for (Presence presence : presences) {
            if (presence.getSpot().getStation().getId() == stationOfDepartureId) {
                isDeparturePassed = true;
                datetimeDeparture = train.getDateOfDeparture()
                        .atTime(train.getRoute().getTimeOfDeparture())
                        .plus(presence.getSpot().getMinutesSinceDeparture(), ChronoUnit.MINUTES);
                //datetimeDeparture = presence.getInstant();
                minutesDeparture = presence.getSpot().getMinutesSinceDeparture();
            }
            if (presence.getSpot().getStation().getId() == stationOfArrivalId) {
                isArrivalNotPassed = false;
                datetimeArrival = train.getDateOfDeparture()
                        .atTime(train.getRoute().getTimeOfDeparture())
                        .plus(presence.getSpot().getMinutesSinceDeparture(), ChronoUnit.MINUTES);
                //datetimeArrival = presence.getInstant();
                minutesArrival = presence.getSpot().getMinutesSinceDeparture();

            }
            if (isDeparturePassed && isArrivalNotPassed) {
                ticketsAvailable = Math.min((train.getNumberOfSeats()-presence.getNumberOfTickets()), ticketsAvailable);
                presencesPassed.add(presence);
            }
        }

        if (ticketsAvailable == 0){
            throw new RuntimeException("No tickets available");
        }
        LocalDateTime datetimeOfPurchase = LocalDateTime.now();
        if (ChronoUnit.MINUTES.between(datetimeOfPurchase, datetimeDeparture) < 10) {
            throw new RuntimeException("Less than 10 minutes before departure");
        }

        Station stationOfDeparture = stationRepository.findOne(stationOfDepartureId);
        Station stationOfArrival = stationRepository.findOne(stationOfArrivalId);

        Account account = accountRepository.findOne(accountId);

        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setDateOfBirth(dateOfBirth);
        ticket.setStationOfDeparture(stationOfDeparture);
        ticket.setStationOfArrival(stationOfArrival);
        ticket.setDatetimeOfPurchase(datetimeOfPurchase);
        ticket.setAccount(account);

        BigDecimal price = (train.getRoute().getPricePerMinute()
                .multiply(new BigDecimal(train.getPriceCoefficient()))
                .multiply(new BigDecimal(minutesArrival-minutesDeparture))).setScale(2, BigDecimal.ROUND_HALF_UP);
        ticket.setPrice(price);

        int ticketId = ticketRepository.save(ticket).getId();

        presencesPassed.forEach(presence -> {
            int ticketsPurchased = presence.getNumberOfTickets();
            ticketsPurchased++;
            presence.setNumberOfTickets(ticketsPurchased);
        });

        presenceRepository.save(presencesPassed);

        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setId(ticketId);
        ticketInfo.setTrainId(trainId);
        ticketInfo.setFirstName(firstName);
        ticketInfo.setLastName(lastName);
        ticketInfo.setDateOfBirth(dateOfBirth);
        ticketInfo.setStationOfDeparture(stationOfDeparture.getTitle());
        ticketInfo.setStationOfArrival(stationOfArrival.getTitle());
        ticketInfo.setDatetimeOfDeparture(datetimeDeparture.toInstant(ZoneOffset.ofHours(stationOfDeparture.getTimezone())));
        ticketInfo.setDatetimeOfArrival(datetimeArrival.toInstant(ZoneOffset.ofHours(stationOfArrival.getTimezone())));
        ticketInfo.setDatetimeOfPurchase(datetimeOfPurchase);
        ticketInfo.setPrice(price);
        return ticketInfo;
    }


    @Override
    public TicketInfo getTicketInfoByTicketId(int ticketId) {
        TicketInfo ticketInfo = new TicketInfo();
        Ticket ticket = ticketRepository.findOne(ticketId);
        Train train = ticket.getTrain();
        ticketInfo.setId(ticketId);
        ticketInfo.setTrainId(train.getId());
        ticketInfo.setFirstName(ticket.getFirstName());
        ticketInfo.setLastName(ticket.getLastName());
        ticketInfo.setDateOfBirth(ticket.getDateOfBirth());
        ticketInfo.setStationOfDeparture(ticket.getStationOfDeparture().getTitle());
        ticketInfo.setStationOfArrival(ticket.getStationOfArrival().getTitle());
        Presence presenceDeparture = presenceRepository.findByTrainIdAndSpotId(train.getId(), spotRepository.findByStationIdAndRouteId(ticket.getStationOfDeparture().getId(), train.getRoute().getId()).getId());
        Presence presenceArrival = presenceRepository.findByTrainIdAndSpotId(train.getId(), spotRepository.findByStationIdAndRouteId(ticket.getStationOfArrival().getId(), train.getRoute().getId()).getId());
        ticketInfo.setDatetimeOfDeparture(presenceDeparture.getInstant());
        ticketInfo.setDatetimeOfArrival(presenceArrival.getInstant());
        ticketInfo.setDatetimeOfPurchase(ticket.getDatetimeOfPurchase());
        ticketInfo.setPrice(ticket.getPrice());
        return ticketInfo;
    }
}
