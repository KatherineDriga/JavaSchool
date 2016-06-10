package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import org.hibernate.Query;
import org.hibernate.Session;


public class StationRepositoryImpl extends BaseRepositoryImpl implements  BaseRepository, StationRepository {

    public StationRepositoryImpl(Session session) {
        super(session);
    }


    @Override
    public Station findById(int id) {
        Station station = null;
        String hql = "FROM Station WHERE id = :id";
        Query query = getSession().createQuery(hql).setInteger("id", id);
        if (station == null){
            throw new RuntimeException();
        }
        return station;
    }

    @Override
    public void save(Object station) {
        getSession().saveOrUpdate(station);
    }

    @Override
    public void delete(int id) {
        String hql = "DELETE FROM Station WHERE id = :id";
        Query query = getSession().createQuery(hql).setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public Station findByTitle(String title) {
        Station station = null;
        String hql = "FROM Station WHERE title = :title";
        Query query = getSession().createQuery(hql).setString("title", title);
        station = (Station)query.uniqueResult();
        if (station == null){
            throw new RuntimeException();
        }
        return station;
    }
}

