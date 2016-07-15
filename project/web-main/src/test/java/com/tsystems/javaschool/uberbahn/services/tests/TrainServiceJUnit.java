package com.tsystems.javaschool.uberbahn.services.tests;

import com.tsystems.javaschool.uberbahn.entities.Train;
import com.tsystems.javaschool.uberbahn.repositories.*;
import com.tsystems.javaschool.uberbahn.services.TrainService;
import com.tsystems.javaschool.uberbahn.services.TrainServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.webmain.WebInizializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInizializer.class})
public class TrainServiceJUnit {

    private RouteRepository routeRepository;
    private StationRepository stationRepository;
    private SpotRepository spotRepository;
    private TrainRepository trainRepository;
    private PresenceRepository presenceRepository;
    private TrainService trainService;
    private Train train;

    @Before
    public void setupMock() {
        train = mock(Train.class);
        routeRepository = mock(RouteRepository.class);
        stationRepository = mock(StationRepository.class);
        spotRepository = mock(SpotRepository.class);
        trainRepository = mock(TrainRepository.class);
        presenceRepository = mock(PresenceRepository.class);
        trainService = new TrainServiceImpl(routeRepository, stationRepository, spotRepository, trainRepository, presenceRepository);
    }

    @Test(expected = BusinessLogicException.class)
    public void getTrainsWithInvalidDates() {
        LocalDateTime since = LocalDateTime.now();
        LocalDateTime until = since.minusHours(10);
        System.out.println("Stubbing to get trains with invalid dates");
        System.out.println("trainService.getAll should throw BusinessLogicException");
        trainService.getAll(1, 1, since, until);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTrainWithEmptyDate() {
        LocalDate date = null;
        System.out.println("Stubbing to create train with unfilled date");
        System.out.println("trainService.create should throw BusinessLogicException");
        trainService.create(1, date, 100, 1.2);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTrainWithExistingDate () {
        int routeId = 1;
        LocalDate enteredDate = LocalDate.now().plusDays(2);
        System.out.println("Stubbing to create train with already existing date");
        when(trainRepository.findByRouteIdAndDateOfDeparture(routeId, enteredDate)).thenReturn(train);
        System.out.println("trainService.create should throw BusinessLogicException");
        trainService.create(routeId, enteredDate, 100, 1.2);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTrainWithInvalidPriceCoefficient() {
        double priceCoefficient = -2.0;
        System.out.println("Stubbing to create train with invalid price coefficient");
        System.out.println("trainService.create should throw BusinessLogicException");
        trainService.create(1, LocalDate.now().plusDays(3), 100, priceCoefficient);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTrainWithInvalidNumberOfSeats() {
        int numberOfSeats = 0;
        System.out.println("Stubbing to create train with invalid number of seats");
        System.out.println("trainService.create should throw BusinessLogicException");
        trainService.create(1, LocalDate.now().plusDays(3),numberOfSeats, 1.2);
    }


}
