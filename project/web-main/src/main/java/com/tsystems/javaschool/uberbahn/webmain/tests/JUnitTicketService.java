package com.tsystems.javaschool.uberbahn.webmain.tests;

import com.tsystems.javaschool.uberbahn.entities.*;
import com.tsystems.javaschool.uberbahn.repositories.*;
import com.tsystems.javaschool.uberbahn.services.TicketService;
import com.tsystems.javaschool.uberbahn.services.TicketServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.webmain.WebInizializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;


import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInizializer.class})
public class JUnitTicketService {

    private TrainRepository trainRepository;
    private AccountRepository accountRepository;
    private TicketRepository ticketRepository;
    private SpotRepository spotRepository;
    private PresenceRepository presenceRepository;
    private TicketService ticketService;
    private Ticket ticket;
    private Train train;
    private Presence presenceDeparture;
    private Presence presenceArrival;
    private Spot spotDeparture;
    private Spot spotArrival;
    private Station stationDeparture;
    private Station stationArrival;
    private Account account;

    @Before
    public void setupMock() {
        ticket = mock(Ticket.class);
        train = mock(Train.class);
        presenceDeparture = mock(Presence.class);
        presenceArrival = mock(Presence.class);
        account = mock(Account.class);
        spotDeparture = mock(Spot.class);
        spotArrival = mock(Spot.class);
        stationDeparture = mock(Station.class);
        stationArrival = mock(Station.class);
        trainRepository = mock(TrainRepository.class);
        accountRepository = mock(AccountRepository.class);
        ticketRepository = mock(TicketRepository.class);
        spotRepository = mock(SpotRepository.class);
        presenceRepository = mock(PresenceRepository.class);
        ticketService = new TicketServiceImpl(trainRepository, accountRepository, ticketRepository, spotRepository, presenceRepository);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTicketWithNoSeatsAvailable () {
        int trainId = 1;
        int stationOfDepartureId = 1;
        int stationOfArrivalId = 2;
        int ticketsAvailable = 0;
        System.out.println("Stubbing to create ticket with no seats available");
        when(trainRepository.findOne(trainId)).thenReturn(train);
        when(ticketService.countTicketsAvailable(trainId, stationOfDepartureId, stationOfArrivalId)).thenReturn(ticketsAvailable);
        System.out.println("ticketService.create should throw BusinessLogicException");
        ticketService.create(trainId, stationOfDepartureId, stationOfArrivalId, "firstName", "lastName", LocalDate.of(2000, 10, 1), "login");
    }


    /*@Test
    public void testTicketPurchase() {
        int trainId = 1;
        int stationOfDepartureId = 1;
        int stationOfArrivalId = 2;
        String accountLogin = "login";
        stationDeparture.setTimezone(0);
        stationArrival.setTimezone(0);
        spotDeparture.setStation(stationDeparture);
        spotArrival.setStation(stationArrival);
        presenceDeparture.setSpot(spotDeparture);
        presenceArrival.setSpot(spotArrival);
        int ticketsAvailable = 30;
        presenceDeparture.setInstant(Instant.now().plusSeconds(3600));
        presenceArrival.setInstant(Instant.now().plusSeconds(7200));

        when(trainRepository.findOne(trainId)).thenReturn(train);
        when(ticketService.countTicketsAvailable(trainId, stationOfDepartureId, stationOfArrivalId)).thenReturn(ticketsAvailable);
        when(presenceRepository.findByTrainIdAndStationId(trainId, stationOfDepartureId)).thenReturn(presenceDeparture);
        when(presenceRepository.findByTrainIdAndStationId(trainId, stationOfArrivalId)).thenReturn(presenceArrival);
        when(accountRepository.findByLogin(accountLogin)).thenReturn(account);

        ticketService.create(trainId, stationOfDepartureId, stationOfArrivalId, "firstName", "lastName", LocalDate.of(2000, 10, 1), "login");

        verify(ticketService, atLeastOnce()).countTicketsAvailable(trainId, stationOfDepartureId, stationOfArrivalId);

        InOrder order = inOrder(ticketService);
        order.verify(ticketService).countTicketsAvailable(trainId, stationOfDepartureId, stationOfArrivalId);
        order.verify(ticketRepository).save(ticket);
    }*/
}
