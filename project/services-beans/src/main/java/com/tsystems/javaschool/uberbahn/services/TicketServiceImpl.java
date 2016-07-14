package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.entities.Presence;
import com.tsystems.javaschool.uberbahn.entities.Ticket;
import com.tsystems.javaschool.uberbahn.entities.Train;
import com.tsystems.javaschool.uberbahn.repositories.*;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.services.errors.DatabaseException;
import com.tsystems.javaschool.uberbahn.transports.TicketInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TrainRepository trainRepository;
    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;
    private final SpotRepository spotRepository;
    private final PresenceRepository presenceRepository;

    @Autowired
    public TicketServiceImpl(TrainRepository trainRepository, AccountRepository accountRepository, TicketRepository ticketRepository, SpotRepository spotRepository, PresenceRepository presenceRepository) {
        this.trainRepository = trainRepository;
        this.accountRepository = accountRepository;
        this.ticketRepository = ticketRepository;
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
                ticketsAvailable = Math.min(train.getNumberOfSeats() - presence.getNumberOfTicketsPurchased(), ticketsAvailable);
            }
        }
        return ticketsAvailable;
    }

    @Override
    public List<TicketInfo> getTicketInfos(int accountId, LocalDateTime since, LocalDateTime until) {
        List<TicketInfo> ticketInfos = ticketRepository.getByAccountIdSinceAndUntil(accountId, since, until).stream().map(ticket -> {
            TicketInfo ticketInfo = new TicketInfo();
            ticketInfo.setId(ticket.getId());
            ticketInfo.setTrainId(ticket.getTrain().getId());
            ticketInfo.setStationOfDeparture(ticket.getStationOfDeparture().getTitle());
            Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(ticket.getTrain().getId(), ticket.getStationOfDeparture().getId());
            LocalDateTime datetimeDeparture = LocalDateTime.ofInstant(presenceDeparture.getInstant(), ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
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
            return ticketInfo;
        }).collect(Collectors.toList());

        Collections.sort(ticketInfos, (TicketInfo t1, TicketInfo t2) ->
                t2.getDatetimeOfDeparture().compareTo(t1.getDatetimeOfDeparture()));
        return ticketInfos;
    }

    @Override
    public List<TicketInfo> getTicketInfos(LocalDateTime since, LocalDateTime until) {
        return ticketRepository.getBySinceAndUntil(since, until).stream().map(ticket -> {
            TicketInfo ticketInfo = new TicketInfo();
            ticketInfo.setId(ticket.getId());
            ticketInfo.setTrainId(ticket.getTrain().getId());
            ticketInfo.setStationOfDeparture(ticket.getStationOfDeparture().getTitle());
            Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(ticket.getTrain().getId(), ticket.getStationOfDeparture().getId());
            LocalDateTime datetimeDeparture = LocalDateTime.ofInstant(presenceDeparture.getInstant(), ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
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
            ticketInfo.setLogin(ticket.getAccount().getLogin());
            return ticketInfo;
        }).collect(Collectors.toList());

    }


    @Override
    public TicketInfo create(int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, String accountLogin) {
        Train train = trainRepository.findOne(trainId);

        train.getTickets().forEach(ticket -> {
            if (ticket.getFirstName().equals(firstName) && ticket.getLastName().equals(lastName) && ticket.getDateOfBirth().isEqual(dateOfBirth)) {
                throw new BusinessLogicException("Passenger is already registered");
            }
        });

        int ticketsAvailable = countTicketsAvailable(trainId, stationOfDepartureId, stationOfArrivalId);
        if (ticketsAvailable == 0) {
            throw new BusinessLogicException("No tickets available");
        }

        Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(trainId, stationOfDepartureId);
        Presence presenceArrival = presenceRepository.findByTrainIdAndStationId(trainId, stationOfArrivalId);
        Instant datetimeOfPurchase = LocalDateTime.now().toInstant(ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
        if (ChronoUnit.MINUTES.between(datetimeOfPurchase, presenceDeparture.getInstant()) < 10) {
            throw new BusinessLogicException("Less than 10 minutes before departure");
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
                .multiply(BigDecimal.valueOf(train.getPriceCoefficient()))
                .multiply(new BigDecimal(minutesArrival - minutesDeparture))).setScale(2, BigDecimal.ROUND_HALF_UP);
        ticket.setPrice(price);

        int ticketId;
        try {
            ticketId = ticketRepository.save(ticket).getId();
        } catch (PersistenceException | NullPointerException ex) {
            throw new DatabaseException("Database writing error", ex);
        }

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

        try {
            presenceRepository.save(presencesPassed);
        } catch (PersistenceException | NullPointerException ex) {
            throw new DatabaseException("Database writing error", ex);
        }

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
