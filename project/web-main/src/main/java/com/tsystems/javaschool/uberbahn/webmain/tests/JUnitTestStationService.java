package com.tsystems.javaschool.uberbahn.webmain.tests;

import com.tsystems.javaschool.uberbahn.entities.Station;
import com.tsystems.javaschool.uberbahn.repositories.PresenceRepository;
import com.tsystems.javaschool.uberbahn.repositories.StationRepository;
import com.tsystems.javaschool.uberbahn.services.StationService;
import com.tsystems.javaschool.uberbahn.services.StationServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.webmain.WebInizializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInizializer.class})
public class JUnitTestStationService {

    private StationRepository stationRepository;
    private PresenceRepository presenceRepository;
    private StationService stationService;
    private Station station;

    @Before
    public void setupMock() {
        station = mock(Station.class);
        stationRepository = mock(StationRepository.class);
        presenceRepository = mock(PresenceRepository.class);
        stationService = new StationServiceImpl(stationRepository, presenceRepository);
    }

    @Test(expected = BusinessLogicException.class)
    public void createStationWithExistingTitle() {
        String enteredTitle = "title";
        System.out.println("Stubbing to create station with already existing title");
        when(stationRepository.findByTitle(enteredTitle)).thenReturn(station);
        System.out.println("stationService.create should throw BusinessLogicException");
        stationService.create(enteredTitle, 0);
    }

    @Test(expected = PersistenceException.class)
    public void databaseWritingError() {
        System.out.println("Stubbing to throw PersistenceException");
        when(stationRepository.save(station)).thenReturn(null);
        System.out.println("stationService.create should throw PersistenceException");
        stationService.create("title", 0);
    }

}
