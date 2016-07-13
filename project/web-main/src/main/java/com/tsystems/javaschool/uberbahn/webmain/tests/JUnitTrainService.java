package com.tsystems.javaschool.uberbahn.webmain.tests;

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

import javax.persistence.PersistenceException;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInizializer.class})
public class JUnitTrainService {

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
    public void createTrainWithExistingDate () {
        int routeId = 1;
        LocalDate enteredDate = LocalDate.now().plusDays(2);
        System.out.println("Stubbing to create train with already existing date");
        when(trainRepository.findByRouteIdAndDateOfDeparture(routeId, enteredDate)).thenReturn(train);
        System.out.println("trainService.create should throw BusinessLogicException");
        trainService.create(routeId, enteredDate, 100, 1.2);
    }

    @Test(expected = PersistenceException.class)
    public void databaseWritingError() {
        System.out.println("Stubbing to throw PersistenceException");
        when(trainRepository.save(train)).thenReturn(null);
        System.out.println("trainService.create should throw PersistenceException");
        trainService.create(1, LocalDate.now(), 100, 1.2);
    }

}
