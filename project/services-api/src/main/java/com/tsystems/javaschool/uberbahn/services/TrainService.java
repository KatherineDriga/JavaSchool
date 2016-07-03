package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.PassengerInfo;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface TrainService {

    Collection<TrainInfo> getAll(int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until);

    TrainInfo create(int routeId, LocalDate dateOfDeparture, int numberOfSeats, double priceCoefficient);

    Collection<TrainInfo> getTrainInfos (int routeId);

    Collection<PassengerInfo> getPassengerInfo (int trainId);
}
