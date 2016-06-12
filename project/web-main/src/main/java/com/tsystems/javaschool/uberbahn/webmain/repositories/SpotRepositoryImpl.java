package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;
import org.hibernate.Session;


public class SpotRepositoryImpl extends BaseRepositoryImpl<Spot> implements  SpotRepository {

    public SpotRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Spot findById(int id) {
        return getSession().get(Spot.class, id);
    }
}

