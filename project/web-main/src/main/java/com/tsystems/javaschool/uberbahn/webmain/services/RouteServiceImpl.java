package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.entities.Spot;
import com.tsystems.javaschool.uberbahn.webmain.entities.Train;
import com.tsystems.javaschool.uberbahn.webmain.repositories.*;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteSpotInfo;
import org.hibernate.Session;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RouteServiceImpl extends BaseServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final SpotRepository spotRepository;


    public RouteServiceImpl(Session session) {
        super(session);
        this.routeRepository = new RouteRepositoryImpl(session);
        this.stationRepository = new StationRepositoryImpl(session);
        this.spotRepository = new SpotRepositoryImpl(session);
    }



    @Override
    public boolean existsRoute(String title) {
        Route route = routeRepository.findByTitle(title);
        if (route != null) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<RouteInfo> getAll() {
        Collection<RouteInfo> result = new ArrayList<>();

        routeRepository.findAll().forEach(route -> {
            RouteInfo info = new RouteInfo();
            info.setId(route.getId());
            info.setTitle(route.getTitle());
            result.add(info);
        });
        return result;
    }

    @Override
    public RouteInfo create(String title, LocalTime timeOfDeparture, List<Integer> stationIds, List<Integer> minutesSinceDepartures) {
        Route route = new Route();
        route.setTitle(title);
        route.setTimeOfDeparture(timeOfDeparture);
        route = routeRepository.save(route);

        for (int i=0; i<stationIds.size(); i++) {
            Spot spot = new Spot();
            spot.setRoute(route);
            spot.setMinutesSinceDeparture(minutesSinceDepartures.get(i));
            spot.setStation(stationRepository.findById(stationIds.get(i)));
            spotRepository.save(spot);

        }
        return getById(route.getId());
    }

    @Override
    public RouteInfo getById(int id) {
        Route route = routeRepository.findById(id);
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setId(route.getId());
        routeInfo.setTitle(route.getTitle());
        routeInfo.setTimeOfDeparture(route.getTimeOfDeparture());
        Collection<RouteSpotInfo> routeSpotInfos = new ArrayList<>();
        route.getSpots().forEach(spot -> {
            RouteSpotInfo spotInfo = new RouteSpotInfo();
            spotInfo.setStationTitle(spot.getStation().getTitle());
            spotInfo.setMinutesSinceDeparture(spot.getMinutesSinceDeparture());
            routeSpotInfos.add(spotInfo);
        });
        routeInfo.setSpots(routeSpotInfos);
        return routeInfo;
    }


}
