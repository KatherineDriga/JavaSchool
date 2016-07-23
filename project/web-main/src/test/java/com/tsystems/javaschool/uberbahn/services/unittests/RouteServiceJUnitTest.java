package com.tsystems.javaschool.uberbahn.services.unittests;


import com.tsystems.javaschool.uberbahn.entities.Route;
import com.tsystems.javaschool.uberbahn.entities.Spot;
import com.tsystems.javaschool.uberbahn.repositories.RouteRepository;
import com.tsystems.javaschool.uberbahn.repositories.SpotRepository;
import com.tsystems.javaschool.uberbahn.repositories.StationRepository;
import com.tsystems.javaschool.uberbahn.services.RouteService;
import com.tsystems.javaschool.uberbahn.services.RouteServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.webmain.WebInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInitializer.class})
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
        routeService.create(enteredTitle, LocalTime.of(20, 10), Arrays.asList(1, 2), Arrays.asList(0, 100), BigDecimal.valueOf(1.5));
    }

    @Test(expected = BusinessLogicException.class)
    public void createRouteWithEmptyFields() {
        System.out.println("Stubbing to create route with unfilled fields");
        System.out.println("routeService.create should throw BusinessLogicException");
        routeService.create(null, LocalTime.of(20, 10), null, Arrays.asList(0, 100), BigDecimal.valueOf(1.5));
    }

    @Test(expected = BusinessLogicException.class)
    public void createRouteWithInvalidPricePerMinute() {
        BigDecimal pricePerMinute = BigDecimal.valueOf(-2.5);
        System.out.println("Stubbing to create route with invalid price per minute");
        System.out.println("routeService.create should throw BusinessLogicException");
        routeService.create("title", LocalTime.of(20, 10), Arrays.asList(1, 2), Arrays.asList(0, 100), pricePerMinute);
    }

    @Test(expected = BusinessLogicException.class)
    public void checkIfStationsReiterate () {
        List<Integer> stationIds = Arrays.asList(1, 1);
        System.out.println("Stubbing to create route with reiterating stations");
        routeService.create("title", LocalTime.of(20, 10), stationIds, Arrays.asList(0, 100), BigDecimal.valueOf(1.5));
    }

}
