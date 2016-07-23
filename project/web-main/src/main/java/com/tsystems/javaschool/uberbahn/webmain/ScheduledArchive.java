package com.tsystems.javaschool.uberbahn.webmain;

import com.tsystems.javaschool.uberbahn.services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableScheduling
@Lazy(false)
public class ScheduledArchive {

    private final PresenceService presenceService;

    @Autowired
    public ScheduledArchive(PresenceService presenceService) {

        this.presenceService = presenceService;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 2 * * ?")
    private void archiveTrains() {
        presenceService.archive();
    }
}
