package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.TicketInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    TicketInfo create (int trainId, int stationOfDepartureId, int stationOfArrivalId, String firstName, String lastName, LocalDate dateOfBirth, String accountLogin);

    TicketInfo getTicketInfo(int ticketId);

    int countTicketsAvailable(int trainId, int stationOfDepartureId, int stationOfArrivalId);

    List<TicketInfo> getTicketInfos(int accountId, LocalDateTime since, LocalDateTime until);

    List<TicketInfo> getTicketInfos(LocalDateTime since, LocalDateTime until);
}

