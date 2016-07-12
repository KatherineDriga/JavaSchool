package com.tsystems.javaschool.uberbahn.webmain;

import com.tsystems.javaschool.uberbahn.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Lazy(false)
@Profile("development")
public class DevelopmentDatabasePopulator {

    private final StationService stationService;
    private final AccountService accountService;
    private final RouteService routeService;
    private final TrainService trainService;
    private final TicketService ticketService;

    @Autowired
    public DevelopmentDatabasePopulator(StationService stationService, AccountService accountService, RouteService routeService, TrainService trainService, TicketService ticketService) {
        this.stationService = stationService;
        this.accountService = accountService;
        this.routeService = routeService;
        this.trainService = trainService;
        this.ticketService = ticketService;
    }

    @PostConstruct
    private void onCreated() {

        createAccounts();
        createStations();
        createRoutes();
        createTrains();
        createTickets();
    }

    private void createStations() {
        stationService.create("Moscow", 3);
        stationService.create("St-Petersburg", 3);
        stationService.create("Tver", 3);
        stationService.create("Rostov", -2);
        stationService.create("Ekaterinburg", 5);
        stationService.create("Sochi", 3);
    }

    private void createAccounts() {
        accountService.create("empl", "empl@example.com", "123", "Elena", "Pavlova", LocalDate.of(1992, 10, 15), true);
        accountService.create("user", "user@example.com", "123", "Oleg", "Ivanov", LocalDate.of(1988, 12, 3), false);
    }

    private void createRoutes() {
        List<Integer> stationIds = new ArrayList<>();
        stationIds.add(2);
        stationIds.add(3);
        stationIds.add(1);
        stationIds.add(4);
        stationIds.add(6);
        List<Integer> minutesSinceDepaertures = new ArrayList<>();
        minutesSinceDepaertures.add(0);
        minutesSinceDepaertures.add(460);
        minutesSinceDepaertures.add(700);
        minutesSinceDepaertures.add(1360);
        minutesSinceDepaertures.add(2760);
        routeService.create("St.-Petersburg - Sochi", java.time.LocalTime.of(6, 50), stationIds, minutesSinceDepaertures, new BigDecimal(1.5));
    }

    private void createTrains() {
        trainService.create(1, LocalDate.now().plusDays(2), 100, 1.3);
        trainService.create(1, LocalDate.now().plusDays(4), 100, 1.4);
    }

    private void createTickets() {
        ticketService.create(1, 2, 6, "Olga", "Petrova", LocalDate.of(1995, 3, 4), "empl");
        ticketService.create(2, 3, 4, "Oleg", "Ivanov", LocalDate.of(1988, 12, 3), "user");
    }
}
