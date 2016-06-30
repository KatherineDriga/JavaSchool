package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.transports.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface TrainService {

    TrainTimetable getTimetable (int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until);

    Collection<TrainInfo> getAll(int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until);

    TrainInfo create(int routeId, LocalDate dateOfDeparture, int numberOfSeats, double priceCoefficient);

    Collection<TrainInfo> getTrainInfos (int routeId);

    Collection<PassengerInfo> getPassengerInfo (int trainId);
}
