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
    private final PresenceRepository presenceRepository;

    @Autowired
    public TicketServiceImpl(TrainRepository trainRepository, AccountRepository accountRepository, TicketRepository ticketRepository, PresenceRepository presenceRepository) {
        this.trainRepository = trainRepository;
        this.accountRepository = accountRepository;
        this.ticketRepository = ticketRepository;
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
            return getTicketInfo(ticket);
        }).collect(Collectors.toList());
        Collections.sort(ticketInfos, (TicketInfo t1, TicketInfo t2) ->
                t2.getDatetimeOfDeparture().compareTo(t1.getDatetimeOfDeparture()));
        return ticketInfos;
    }

    @Override
    public List<TicketInfo> getTicketInfos(LocalDateTime since, LocalDateTime until) {
        return ticketRepository.getBySinceAndUntil(since, until).stream().map(ticket -> {
            return getTicketInfo(ticket);
        }).collect(Collectors.toList());

    }

    @Override
    public TicketInfo create(int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, String accountLogin) {

        checkFields(trainId, stationOfDepartureId, stationOfArrivalId, firstName, lastName, dateOfBirth);

        Train train = trainRepository.findOne(trainId);
        Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(trainId, stationOfDepartureId);
        Presence presenceArrival = presenceRepository.findByTrainIdAndStationId(trainId, stationOfArrivalId);
        Instant datetimeOfPurchase = LocalDateTime.now().toInstant(ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
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

        try {
            ticketRepository.save(ticket);
        } catch (PersistenceException | NullPointerException ex) {
            throw new DatabaseException("Error occurred", ex);
        }
        savePresences(trainId, stationOfDepartureId, stationOfArrivalId);

        return getTicketInfo(ticket);
    }


    @Override
    public TicketInfo getTicketInfo(int ticketId) {
        Ticket ticket = ticketRepository.findOne(ticketId);
        return getTicketInfo(ticket);
    }

    private TicketInfo getTicketInfo (Ticket ticket) {
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
        ticketInfo.setRouteTitle(ticket.getTrain().getRoute().getTitle());
        return ticketInfo;
    }

    private void checkFields (int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth) {
        if (firstName == null || lastName == null || dateOfBirth == null) {
            throw new BusinessLogicException("All fields are required");
        }
        if (!allLetters(firstName)) {
            throw new BusinessLogicException("Invalid first name");
        }
        if (!allLetters(lastName)) {
            throw new BusinessLogicException("Invalid last name");
        }
        if (LocalDate.now().isBefore(dateOfBirth)) {
            throw new BusinessLogicException("Invalid Date of Birth");
        }
        Train train = trainRepository.findOne(trainId);
        Presence presenceDeparture = presenceRepository.findByTrainIdAndStationId(train.getId(), stationOfDepartureId);
        int ticketsAvailable = countTicketsAvailable(trainId, stationOfDepartureId, stationOfArrivalId);
        if (ticketsAvailable == 0) {
            throw new BusinessLogicException("No tickets available");
        }
        train.getTickets().forEach(ticket -> {
            if (ticket.getFirstName().equals(firstName) && ticket.getLastName().equals(lastName) && ticket.getDateOfBirth().isEqual(dateOfBirth)) {
                throw new BusinessLogicException("Passenger is already registered");
            }
        });
        Instant datetimeOfPurchase = LocalDateTime.now().toInstant(ZoneOffset.ofHours(presenceDeparture.getSpot().getStation().getTimezone()));
        if (ChronoUnit.MINUTES.between(datetimeOfPurchase, presenceDeparture.getInstant()) < 10) {
            throw new BusinessLogicException("Less than 10 minutes before departure");
        }
    }

    private boolean allLetters (String string) {
        return string.chars().allMatch(x -> Character.isLetter(x));
    }

    private void savePresences (int trainId, int stationOfDepartureId, int stationOfArrivalId) {
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
            throw new DatabaseException("Error occurred", ex);
        }
    }

}
