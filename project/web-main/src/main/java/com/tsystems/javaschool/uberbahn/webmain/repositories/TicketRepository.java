package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Ticket;

import java.util.Collection;

public interface TicketRepository extends BaseRepository<Ticket> {
    Collection<Ticket> getByTrainIdAndStationOfDeparture(int trainId, int stationOfDeparture);
}
