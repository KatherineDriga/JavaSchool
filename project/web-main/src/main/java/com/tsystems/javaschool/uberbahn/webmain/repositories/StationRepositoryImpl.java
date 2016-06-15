package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import org.hibernate.Session;

import java.util.Collection;


public class StationRepositoryImpl extends BaseRepositoryImpl<Station> implements  StationRepository {

    public StationRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Station findById(int id) {

        return getSession().get(Station.class, id);
    }

    @Override
    public Station findByTitle(String title) {
        return (Station) getSession()
                .createQuery(
                        "FROM Station " +
                        "WHERE title = :title")
                .setString("title", title)
                .uniqueResult();
    }

    @Override
    public Collection<Station> findAll() {
        return getSession()
                .createQuery("FROM Station")
                .list();
    }


}

