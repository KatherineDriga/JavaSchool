package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import com.tsystems.javaschool.uberbahn.webmain.entities.Ticket;
import com.tsystems.javaschool.uberbahn.webmain.entities.Train;
import org.hibernate.Query;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public class TrainRepositoryImpl extends BaseRepositoryImpl<Train> implements TrainRepository {

    public TrainRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Train findById(int id) {

        return getSession().get(Train.class, id);

    }


    @Override
    public Collection<Ticket> getTicketsByTrainId(int trainId) {
        return getSession()
                .createQuery("FROM Ticket " +
                        "WHERE train.id = :trainId")
                .setInteger("trainId", trainId)
                .list();
    }
}

