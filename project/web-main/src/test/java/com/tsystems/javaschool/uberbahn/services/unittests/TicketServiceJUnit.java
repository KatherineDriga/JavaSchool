package com.tsystems.javaschool.uberbahn.services.unittests;

import com.tsystems.javaschool.uberbahn.entities.*;
import com.tsystems.javaschool.uberbahn.repositories.*;
import com.tsystems.javaschool.uberbahn.services.TicketService;
import com.tsystems.javaschool.uberbahn.services.TicketServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.webmain.WebInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;


import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInitializer.class})
public class TicketServiceJUnit {

    private TrainRepository trainRepository;
    private AccountRepository accountRepository;
    private TicketRepository ticketRepository;
    private SpotRepository spotRepository;
    private PresenceRepository presenceRepository;
    private TicketService ticketService;
    private Train train;


    @Before
    public void setupMock() {
        train = mock(Train.class);
        trainRepository = mock(TrainRepository.class);
        accountRepository = mock(AccountRepository.class);
        ticketRepository = mock(TicketRepository.class);
        presenceRepository = mock(PresenceRepository.class);
        spotRepository = mock(SpotRepository.class);
        ticketService = new TicketServiceImpl(trainRepository, accountRepository, ticketRepository, presenceRepository, spotRepository);
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
        ticketService.create(trainId, stationOfDepartureId, stationOfArrivalId, "firstName", "lastName", LocalDate.of(2000, 10, 1), "loginUser");
    }

    @Test(expected = BusinessLogicException.class)
    public void createTicketWithEmptyFields() {
        System.out.println("Stubbing to create ticket with unfilled fields");
        System.out.println("ticketService.create should throw BusinessLogicException");
        ticketService.create(1, 1, 2, null, null, LocalDate.of(2000, 10, 1), "loginUser");

    }

    @Test(expected = BusinessLogicException.class)
    public void createTicketWithInvalidDateOfBirth() {
        LocalDate dateOfBirth = LocalDate.now().plusDays(1);
        System.out.println("Stubbing to create ticket with invalid date of birth");
        System.out.println("ticketService.create should throw BusinessLogicException");
        ticketService.create(1, 1, 2, "firstName", "lastName", dateOfBirth, "loginUser");
    }

    @Test(expected = BusinessLogicException.class)
    public void createTicketWithInvalidLastName() {
        String lastName = "123";
        System.out.println("Stubbing to create ticket with invalid last name");
        System.out.println("ticketService.create should throw BusinessLogicException");
        ticketService.create(1, 1, 2, "firstName", lastName, LocalDate.of(2000, 10, 1), "loginUser");
    }


}
