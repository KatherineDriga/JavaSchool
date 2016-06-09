package com.tsystems.javaschool.uberbahn.webmain.repositories;

import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collection;

/**
 * Created by ASUS on 09.06.2016.
 */
public class RouteRepositoryImpl extends BaseRepositoryImpl implements RouteRepository{

    public RouteRepositoryImpl(Session session){
        super(session);
    }


    @Override
    public Collection<Route> findByStation(int stationId) {
        String hql = "FROM Route AS route JOIN Spot AS spot ON route.id = spot.routeId " +
                "WHERE spot.stationId = :stationId";
        Query query = getSession().createQuery(hql).setInteger("stationId", stationId);
        return query.list();
    }
}
