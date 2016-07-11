package com.tsystems.javaschool.uberbahn.webmain;

import com.tsystems.javaschool.uberbahn.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Lazy(false)
@Profile("development")
public class DevelopmentDatabasePopulator {

    private final StationService stationService;

    @Autowired
    public DevelopmentDatabasePopulator(StationService stationService) {
        this.stationService = stationService;
    }

    @PostConstruct
    private void onCreated(){
        stationService.create("Moscow", 3);
    }
}
