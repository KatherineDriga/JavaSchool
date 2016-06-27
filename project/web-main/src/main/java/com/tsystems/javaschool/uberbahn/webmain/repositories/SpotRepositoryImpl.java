package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;
import org.hibernate.Session;

import java.util.Collection;


public class SpotRepositoryImpl extends BaseRepositoryImpl<Spot> implements  SpotRepository {

    public SpotRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Spot findById(int id) {
        return getSession().get(Spot.class, id);
    }

    @Override
    public Spot findByStationIdAndRouteId(int stationId, int routeId) {
        return (Spot) getSession()
                .createQuery("FROM Spot " +
                "WHERE route = :routeId " +
                "AND station = :stationId")
                .setInteger("routeId", routeId)
                .setInteger("stationId", stationId)
                .uniqueResult();
    }

    //@Override
    public Collection<Spot> findAllBetweenStationsByRouteIdAndTime(int routeId, Integer timeSinceDepartureForStA, Integer timeSinceDepartureForStB) {
        return getSession()
                .createQuery("FROM Spot " +
                        "WHERE route = :routeId " +
                        "AND minutesSinceDeparture " +
                        ">= :timeSinceDepartureForStA " +
                        "AND minutesSinceDeparture < :timeSinceDepartureForStB")
                .setInteger("routeId", routeId)
                .setInteger("timeSinceDepartureForStA", timeSinceDepartureForStA)
                .setInteger("timeSinceDepartureForStB", timeSinceDepartureForStB)
                .list();
    }
}

