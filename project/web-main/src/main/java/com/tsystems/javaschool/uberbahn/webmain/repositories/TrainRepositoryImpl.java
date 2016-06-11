package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
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





    /*@Override
    public LocalTime findTimeSinceDepartureByStationIdAndRouteId(int stationId, int routeId) {
        LocalTime time = null;
        String hql = "SELECT s.timeSinceDeparture FROM Spot AS s, s.route AS r " +
                "WHERE r.id = :routeId AND r.station.id = :stationId";
        Query query = getSession().createQuery(hql);
        query.setInteger("stationId", stationId);
        query.setInteger("routeId", routeId);
        time = (LocalTime)query.uniqueResult();
        if (time == null){
            throw new RuntimeException();
        }
        return time;
    }

    @Override
    public Station findDepartsFromStaionByRoute(int routeId) {
        Station station = null;
        String hql = "FROM Station AS st INNER JOIN st.spots AS sp " +
                "WHERE sp.route.id = :routeId " +
                "GROUP BY sp.route.id " +
                "HAVING min(sp.timeSinceDeparture)";
        Query query = getSession().createQuery(hql).setInteger("routeId", routeId);
        station = (Station)query.uniqueResult();
        if (station == null){
            throw new RuntimeException();
        }
        return station;
    }

    @Override
    public Station findArrivesAtStaionByRoute(int routeId) {
        Station station = null;
        String hql = "FROM Station AS st INNER JOIN st.spots AS sp " +
                "WHERE sp.route.id = :routeId " +
                "HAVING max(sp.timeSinceDeparture)";
        Query query = getSession().createQuery(hql).setInteger("routeId", routeId);
        station = (Station)query.uniqueResult();
        if (station == null){
            throw new RuntimeException();
        }
        return station;
    }*/

}

