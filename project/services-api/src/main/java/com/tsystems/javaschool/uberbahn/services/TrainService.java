package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.PassengerInfo;
import com.tsystems.javaschool.uberbahn.transports.TrainInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface TrainService {


    /**
     * Method returns collection of train details for trains passing stations in specified period of time
     * @param stationOfDepartureId id of station of departure
     * @param stationOfArrivalId id of station of arrival
     * @param since date and time (beginning of period)
     * @param until date and time (period ending)
     * @return collection of data transfer objects containing train information
     */
    Collection<TrainInfo> getAll(int stationOfDepartureId, int stationOfArrivalId, LocalDateTime since, LocalDateTime until);


    /**
     * Method creates new train, saves it in database and returns train details
     * @param routeId route id
     * @param dateOfDeparture date of departure
     * @param numberOfSeats total number of seats
     * @param priceCoefficient price coefficient
     * @return data transfer object containing saved train information
     */
    TrainInfo create(int routeId, LocalDate dateOfDeparture, int numberOfSeats, double priceCoefficient);


    /**
     * Method returns collection of train details by route id
     * @param routeId route id
     * @return collection of data transfer objects containing train information
     */
    Collection<TrainInfo> getTrainInfos (int routeId);


    /**
     * Method returns collection of passenger details for train
     * @param trainId train id
     * @return collection of data transfer objects containing passenger information
     */
    Collection<PassengerInfo> getPassengerInfos(int trainId);


    /**
     * Method checks whether train with such route and date of departure already exists
     * @param routeId route id
     * @param dateOfDeparture date of departure
     * @return boolean value
     */
    boolean existsTrain (int routeId, LocalDate dateOfDeparture);


    /**
     * Method returns train details by departure, arrival stations and train id
     * @param stationOfDepartureId id of station of departure
     * @param stationOfArrivalId id of station of arrival
     * @param trainId train id
     * @return data transfer object containing train information
     */
    TrainInfo getByDepartureArrivalAndTrainId (int stationOfDepartureId, int stationOfArrivalId, int trainId);

}
