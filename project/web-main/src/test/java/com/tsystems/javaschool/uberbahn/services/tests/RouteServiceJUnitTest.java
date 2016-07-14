package com.tsystems.javaschool.uberbahn.services.tests;


import com.tsystems.javaschool.uberbahn.entities.Route;
import com.tsystems.javaschool.uberbahn.entities.Spot;
import com.tsystems.javaschool.uberbahn.repositories.RouteRepository;
import com.tsystems.javaschool.uberbahn.repositories.SpotRepository;
import com.tsystems.javaschool.uberbahn.repositories.StationRepository;
import com.tsystems.javaschool.uberbahn.services.RouteService;
import com.tsystems.javaschool.uberbahn.services.RouteServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.services.errors.DatabaseException;
import com.tsystems.javaschool.uberbahn.webmain.WebInizializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInizializer.class})
public class RouteServiceJUnitTest {

    private RouteRepository routeRepository;
    private StationRepository stationRepository;
    private SpotRepository spotRepository;
    private RouteService routeService;
    private Route route;
    private Spot spot;

    @Before
    public void setupMock() {
        route = mock(Route.class);
        spot = mock(Spot.class);
        routeRepository = mock(RouteRepository.class);
        stationRepository = mock(StationRepository.class);
        spotRepository = mock(SpotRepository.class);
        routeService = new RouteServiceImpl(routeRepository, stationRepository, spotRepository);
    }

    @Test(expected = BusinessLogicException.class)
    public void createRouteWithExistingTitle() {
        String enteredTitle = "title";
        System.out.println("Stubbing to create route with already existing title");
        when(routeRepository.findByTitle(enteredTitle)).thenReturn(route);
        System.out.println("routeService.create should throw BusinessLogicException");
        routeService.create(enteredTitle, LocalTime.of(20, 10), new ArrayList<>(1), new ArrayList<>(1), BigDecimal.valueOf(1.5));
    }

    @Test(expected = DatabaseException.class)
    public void databaseWritingError() {
        System.out.println("Stubbing to throw DatabaseException");
        when(spotRepository.save(spot)).thenReturn(null);
        System.out.println("routeService.create should throw DatabaseException");
        routeService.create("title", LocalTime.of(20, 10), new ArrayList<>(1), new ArrayList<>(1), BigDecimal.valueOf(1.5));
    }

}
