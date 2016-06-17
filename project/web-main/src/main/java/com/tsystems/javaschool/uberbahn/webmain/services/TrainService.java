package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.transports.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface TrainService {

    TrainTimetable getTimetable (int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until);
    Collection<TrainInfo> getTrainInfo (int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until);
    AddTrainInfo getAddTrainInfo (int routeId, LocalDate dateOfDeparture, int numberOfSeats);
    Collection<FindTrainInfo> getFindTrainInfo (int routeId);
    Collection<PassengerInfo> getPassengerInfo (int trainId);
}
