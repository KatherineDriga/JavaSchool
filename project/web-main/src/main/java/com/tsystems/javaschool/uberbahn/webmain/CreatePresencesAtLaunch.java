package com.tsystems.javaschool.uberbahn.webmain;

import com.tsystems.javaschool.uberbahn.services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Lazy(false)
public class CreatePresencesAtLaunch {

    private final PresenceService presenceService;

    @Autowired
    public CreatePresencesAtLaunch(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @PostConstruct
    private void onCreated() {
        presenceService.archive();
    }
}
