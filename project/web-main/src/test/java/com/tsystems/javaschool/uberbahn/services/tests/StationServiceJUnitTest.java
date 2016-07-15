package com.tsystems.javaschool.uberbahn.services.tests;

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

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInizializer.class})
public class StationServiceJUnitTest {

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

    @Test(expected = BusinessLogicException.class)
    public void createStationWithEmptyTitleField() {
        System.out.println("Stubbing to create station with unfilled fields");
        System.out.println("stationService.create should throw BusinessLogicException");
        stationService.create(null, 0);
    }

    @Test(expected = BusinessLogicException.class)
    public void createStationWithInvalidTimezone() {
        int timezone = 20;
        System.out.println("Stubbing to create station with invalid timezone");
        System.out.println("stationService.create should throw BusinessLogicException");
        stationService.create("title", timezone);
    }

    @Test
    public void checkIfStationAlreadyExists () {
        String enteredTitle = "title";
        System.out.println("stationService.create should use stationRepository to find by title");
        stationService.create(enteredTitle, 0);
        verify(stationRepository, times(1)).findByTitle(enteredTitle);
    }

}
