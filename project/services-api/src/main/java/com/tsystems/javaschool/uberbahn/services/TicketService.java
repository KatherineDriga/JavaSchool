package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.TicketReport;
import com.tsystems.javaschool.uberbahn.transports.TicketInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    /**
     * Method creates new ticket, saves it in database and returns ticket details
     * @param trainId train id
     * @param stationOfDepartureId station of departure
     * @param stationOfArrivalId station of arrival
     * @param firstName passenger first name
     * @param lastName passenger last name
     * @param dateOfBirth passenger date of birth
     * @param accountLogin login of account through which ticket purchased
     * @return data transfer object containing saved ticket information
     */
    TicketInfo create (int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, String accountLogin);


    /**
     * Method gets ticket from database by its id and returns ticket details
     * @param ticketId ticket id
     * @return data transfer object containing ticket information
     */
    TicketInfo getTicketInfo(int ticketId);


    /**
     * Method counts number of tickets available for purchase
     * @param trainId train id
     * @param stationOfDepartureId id of station of departure
     * @param stationOfArrivalId id of station of arrival
     * @return number of tickets
     */
    int countTicketsAvailable(int trainId, int stationOfDepartureId, int stationOfArrivalId);


    /**
     * Method returns collection of purchased ticket details by account per period
     * @param accountId account id
     * @param since date and time (beginning of period)
     * @param until date and time (period ending)
     * @return collection of data transfer objects containing ticket information
     */
    List<TicketInfo> getTicketInfos(int accountId, LocalDateTime since, LocalDateTime until);


    /**
     * Method returns collection of purchased ticket details per period
     * @param since date and time (beginning of period)
     * @param until date and time (period ending)
     * @return collection of data transfer objects containing ticket information
     */
    List<TicketReport> getTicketInfos(LocalDateTime since, LocalDateTime until);
}

