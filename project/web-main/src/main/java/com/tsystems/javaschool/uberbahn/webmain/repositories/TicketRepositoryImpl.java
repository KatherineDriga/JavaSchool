package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Ticket;
import org.hibernate.Session;

import java.util.Collection;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Ticket> implements TicketRepository {
    public TicketRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Ticket findById(int id) {
        return getSession().get(Ticket.class, id);
    }

    @Override
    public Collection<Ticket> getByTrainIdAndStationOfDeparture(int trainId, int stationOfDeparture) {
        return getSession()
                .createQuery("FROM Ticket " +
                        "WHERE train = :trainId " +
                        "AND stationOfDeparture = :stationOfDeparture")
                .setInteger("trainId", trainId)
                .setInteger("stationOfDeparture", stationOfDeparture)
                .list();
    }

}
