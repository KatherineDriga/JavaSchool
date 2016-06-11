package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collection;


public class RouteRepositoryImpl extends BaseRepositoryImpl<Route> implements RouteRepository{

    public RouteRepositoryImpl(Session session){
        super(session);
    }


    @Override
    public Collection<Route> findByStationId(int stationId) {
        String hql = "FROM Route AS r JOIN Spot AS s ON r.id = s.routeId " +
                "WHERE s.stationId = :stationId";
        Query query = getSession().createQuery(hql).setInteger("stationId", stationId);
        return query.list();
    }

    @Override
    public Route findById(int id) {
        return getSession().get(Route.class, id);
    }
}
