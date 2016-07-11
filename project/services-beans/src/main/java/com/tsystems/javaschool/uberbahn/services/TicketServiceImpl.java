package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.entities.Presence;
import com.tsystems.javaschool.uberbahn.entities.Ticket;
import com.tsystems.javaschool.uberbahn.entities.Train;
import com.tsystems.javaschool.uberbahn.repositories.*;
import com.tsystems.javaschool.uberbahn.services.TicketService;
import com.tsystems.javaschool.uberbahn.transports.TicketInfo;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;


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
    public int countTicketsAvailable(int trainId, int stationOfDepartureId, int stationOfArrivalId) {
        Train train = trainRepository.findOne(trainId);
        Collection<Presence> presences = train.getPresences();
        int ticketsAvailable = train.getNumberOfSeats();
        boolean isDeparturePassed = false;
        boolean isArrivalNotPassed = true;
        for (Presence presence : presences) {
            if (presence.getSpot().getStation().getId() == stationOfDepartureId) {
                isDeparturePassed = true;
            }
            if (presence.getSpot().getStation().getId() == stationOfArrivalId) {
                isArrivalNotPassed = false;
            }
            if (isDeparturePassed && isArrivalNotPassed) {
                ticketsAvailable = Math.min((train.getNumberOfSeats()-presence.getNumberOfTicketsPurchased()), ticketsAvailable);
            }
        }
        return ticketsAvailable;
    }

    @Override
    public List<TicketInfo> getTicketInfos(int accountId, LocalDateTime since, LocalDateTime until) {
        Collection<Ticket> tickets = ticketRepository.getByAccountId(accountId);
        List<TicketInfo> ticketInfos = new ArrayList<>();
        tickets.forEach(ticket -> {
            Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(ticket.getTrain().getId(), ticket.getStationOfDeparture().getId());
            LocalDateTime datetimeDeparture = LocalDateTime.ofInstant(presenceDeparture.getInstant(), ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
            if (datetimeDeparture.isAfter(since) && datetimeDeparture.isBefore(until)) {
                TicketInfo ticketInfo = new TicketInfo();
                ticketInfo.setId(ticket.getId());
                ticketInfo.setTrainId(ticket.getTrain().getId());
                ticketInfo.setStationOfDeparture(ticket.getStationOfDeparture().getTitle());
                ticketInfo.setDatetimeOfDeparture(datetimeDeparture);
                ticketInfo.setStationOfArrival(ticket.getStationOfArrival().getTitle());
                Presence presenceArrival = presenceRepository.findByTrainIdAndStationId(ticket.getTrain().getId(), ticket.getStationOfArrival().getId());
                LocalDateTime datetimeArrival = LocalDateTime.ofInstant(presenceArrival.getInstant(), ZoneOffset.ofHours(presenceArrival.getSpot().getStation().getTimezone()));
                ticketInfo.setDatetimeOfArrival(datetimeArrival);
                ticketInfo.setFirstName(ticket.getFirstName());
                ticketInfo.setLastName(ticket.getLastName());
                ticketInfo.setDateOfBirth(ticket.getDateOfBirth());
                ticketInfo.setDatetimeOfPurchase(ticket.getDatetimeOfPurchase());
                ticketInfo.setPrice(ticket.getPrice());
                ticketInfos.add(ticketInfo);
            }
        });

        Collections.sort(ticketInfos, (TicketInfo t1, TicketInfo t2) ->
                t2.getDatetimeOfDeparture().compareTo(t1.getDatetimeOfDeparture()));
        return ticketInfos;
    }


    @Override
    public TicketInfo create(int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, String accountLogin) {
        Train train = trainRepository.findOne(trainId);

        train.getTickets().forEach(ticket -> {
            if (ticket.getFirstName().equals(firstName) && ticket.getLastName().equals(lastName) && ticket.getDateOfBirth().isEqual(dateOfBirth)) {
                throw new PersistenceException("Passenger is already registered");
            }
        });

        int ticketsAvailable = countTicketsAvailable(trainId, stationOfDepartureId, stationOfArrivalId);
        if (ticketsAvailable == 0){
            throw new PersistenceException("No tickets available");
        }

        Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(trainId, stationOfDepartureId);
        Presence presenceArrival = presenceRepository.findByTrainIdAndStationId(trainId, stationOfArrivalId);
        Instant datetimeOfPurchase = LocalDateTime.now().toInstant(ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
        if (ChronoUnit.MINUTES.between(datetimeOfPurchase, presenceDeparture.getInstant()) < 10) {
            throw new PersistenceException("Less than 10 minutes before departure");
        }

        OffsetDateTime datetimeDeparture = presenceDeparture.getInstant().atOffset(ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
        OffsetDateTime datetimeArrival = presenceArrival.getInstant().atOffset(ZoneOffset.ofHours(presenceArrival.getSpot().getStation().getTimezone()));
        int minutesDeparture = presenceDeparture.getSpot().getMinutesSinceDeparture();
        int minutesArrival = presenceArrival.getSpot().getMinutesSinceDeparture();

        Account account = accountRepository.findByLogin(accountLogin);

        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setDateOfBirth(dateOfBirth);
        ticket.setStationOfDeparture(presenceDeparture.getSpot().getStation());
        ticket.setStationOfArrival(presenceArrival.getSpot().getStation());
        ticket.setDatetimeOfPurchase(LocalDateTime.ofInstant(datetimeOfPurchase, ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone())));
        ticket.setAccount(account);

        BigDecimal price = (train.getRoute().getPricePerMinute()
                .multiply((BigDecimal.valueOf(train.getPriceCoefficient())))
                .multiply(new BigDecimal(minutesArrival-minutesDeparture))).setScale(2, BigDecimal.ROUND_HALF_UP);
        ticket.setPrice(price);

        int ticketId = ticketRepository.save(ticket).getId();

        Collection<Presence> presencesPassed = new ArrayList<>();
        boolean isDeparturePassed = false;
        boolean isArrivalNotPassed = true;
        Collection<Presence> presences = presenceRepository.findByTrainId(trainId);
        for (Presence presence : presences) {
            if (presence.getSpot().getStation().getId() == stationOfDepartureId) {
                isDeparturePassed = true;
            }
            if (presence.getSpot().getStation().getId() == stationOfArrivalId) {
                isArrivalNotPassed = false;
            }
            if (isDeparturePassed && isArrivalNotPassed) {
                presencesPassed.add(presence);
            }
        }
        presencesPassed.forEach(presence -> {
            int ticketsPurchased = presence.getNumberOfTicketsPurchased();
            ticketsPurchased++;
            presence.setNumberOfTicketsPurchased(ticketsPurchased);
        });

        presenceRepository.save(presencesPassed);

        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setId(ticketId);
        ticketInfo.setTrainId(trainId);
        ticketInfo.setFirstName(firstName);
        ticketInfo.setLastName(lastName);
        ticketInfo.setDateOfBirth(dateOfBirth);
        ticketInfo.setStationOfDeparture(presenceDeparture.getSpot().getStation().getTitle());
        ticketInfo.setStationOfArrival(presenceArrival.getSpot().getStation().getTitle());
        ticketInfo.setDatetimeOfDeparture(datetimeDeparture.toLocalDateTime());
        ticketInfo.setDatetimeOfArrival(datetimeArrival.toLocalDateTime());
        ticketInfo.setDatetimeOfPurchase(LocalDateTime.ofInstant(datetimeOfPurchase, ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone())));
        ticketInfo.setPrice(price);
        return ticketInfo;
    }


    @Override
    public TicketInfo getTicketInfo(int ticketId) {
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
        ticketInfo.setDatetimeOfDeparture(LocalDateTime.ofInstant(presenceDeparture.getInstant(), ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone())));
        ticketInfo.setDatetimeOfArrival(LocalDateTime.ofInstant(presenceArrival.getInstant(), ZoneOffset.ofHours(presenceArrival.getSpot().getStation().getTimezone())));
        ticketInfo.setDatetimeOfPurchase(ticket.getDatetimeOfPurchase());
        ticketInfo.setPrice(ticket.getPrice());
        return ticketInfo;
    }

}
