package com.tsystems.javaschool.uberbahn.webmain;

import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.services.RouteService;
import com.tsystems.javaschool.uberbahn.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Lazy(false)
@Profile("development")
public class DevelopmentDatabasePopulator {

    private final StationService stationService;
    private final AccountService accountService;
    private final RouteService routeService;

    @Autowired
    public DevelopmentDatabasePopulator(StationService stationService, AccountService accountService, RouteService routeService) {
        this.stationService = stationService;
        this.accountService = accountService;
        this.routeService = routeService;
    }

    @PostConstruct
    private void onCreated(){

        stationService.create("Moscow", 3);
        stationService.create("St-Petersburg", 3);

        accountService.create("empl", "em", "123", "name", "name", LocalDate.now(), true);

    }
}
