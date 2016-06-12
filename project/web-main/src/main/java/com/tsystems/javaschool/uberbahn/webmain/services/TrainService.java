package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.transports.TrainTimetable;

import java.time.LocalDateTime;

public interface TrainService {

    TrainTimetable getTimetable (String stationOfDeparture, String stationOfArrival, LocalDateTime since, LocalDateTime until);
}
