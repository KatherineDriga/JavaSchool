package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import org.hibernate.Session;

import java.util.Collection;
import java.util.List;


public class RouteRepositoryImpl extends BaseRepositoryImpl<Route> implements RouteRepository {

    public RouteRepositoryImpl(Session session){
        super(session);
    }




    @Override
    public Route findById(int id) {
        return getSession().get(Route.class, id);
    }

    @Override
    public Collection<Route> findByStationId(int stationId) {
        return (Collection<Route>) getSession()
                .createQuery("SELECT r FROM Route AS r JOIN r.spots AS s " +
                        "WHERE s.station.id = :stationId")
                .setInteger("stationId", stationId).list();
    }

    @Override
    public Route findByTitle(String routeTitle) {
        return (Route) getSession()
                .createQuery("FROM Route " +
                        "WHERE title = :routeTitle")
                .setString("routeTitle", routeTitle)
                .uniqueResult();
    }
}


