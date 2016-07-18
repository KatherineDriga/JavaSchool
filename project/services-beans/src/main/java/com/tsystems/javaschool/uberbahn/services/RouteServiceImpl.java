package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.entities.Route;
import com.tsystems.javaschool.uberbahn.entities.Spot;
import com.tsystems.javaschool.uberbahn.repositories.RouteRepository;
import com.tsystems.javaschool.uberbahn.repositories.SpotRepository;
import com.tsystems.javaschool.uberbahn.repositories.StationRepository;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.services.errors.DatabaseException;
import com.tsystems.javaschool.uberbahn.transports.RouteInfo;
import com.tsystems.javaschool.uberbahn.transports.RouteSpotInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class  RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final SpotRepository spotRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, StationRepository stationRepository, SpotRepository  spotRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.spotRepository = spotRepository;
    }

    @Override
    public RouteInfo getById(int id) {
        Route route = routeRepository.findOne(id);
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setId(id);
        routeInfo.setTitle(route.getTitle());
        routeInfo.setTimeOfDeparture(route.getTimeOfDeparture());
        Collection<RouteSpotInfo> routeSpotInfos = route.getSpots().stream().map(spot -> {
            RouteSpotInfo spotInfo = new RouteSpotInfo();
            spotInfo.setStationTitle(spot.getStation().getTitle());
            spotInfo.setMinutesSinceDeparture(spot.getMinutesSinceDeparture());
            return spotInfo;
        }).collect(Collectors.toList());
        routeInfo.setSpots(routeSpotInfos);
        routeInfo.setPricePerMinute(route.getPricePerMinute());
        return routeInfo;
    }

    @Override
    public RouteInfo create(String title, LocalTime timeOfDeparture, List<Integer> stationIds, List<Integer> minutesSinceDepartures, BigDecimal pricePerMinute) {

        checkFields(title, timeOfDeparture, stationIds, minutesSinceDepartures, pricePerMinute);
        Route route = new Route();
        route.setTitle(title);
        route.setTimeOfDeparture(timeOfDeparture);
        route.setPricePerMinute(pricePerMinute);
        try {
            routeRepository.save(route);
        } catch (PersistenceException ex) {
            throw new DatabaseException("Error occurred", ex);
        }
        Collection<RouteSpotInfo> routeSpotInfos = saveSpots(stationIds, route, minutesSinceDepartures);
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setId(route.getId());
        routeInfo.setTitle(title);
        routeInfo.setTimeOfDeparture(timeOfDeparture);
        routeInfo.setSpots(routeSpotInfos);
        routeInfo.setPricePerMinute(route.getPricePerMinute());
        return routeInfo;

    }

    @Override
    public boolean existsRoute(String title) {
        if (routeRepository.findByTitle(title) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<RouteInfo> getAll() {

        return routeRepository.findAllByOrderByTitleAsc().stream().map(route -> {
            RouteInfo info = new RouteInfo();
            info.setId(route.getId());
            info.setTitle(route.getTitle());
            return info;
        }).collect(Collectors.toList());

    }

    private Collection<RouteSpotInfo> saveSpots(List<Integer> stationIds, Route route, List<Integer> minutesSinceDepartures) {
        Collection<RouteSpotInfo> routeSpotInfos = new ArrayList<>();
        for (int i=0; i<stationIds.size(); i++) {
            Spot spot = new Spot();
            spot.setRoute(route);
            spot.setMinutesSinceDeparture(minutesSinceDepartures.get(i));
            spot.setStation(stationRepository.findOne(stationIds.get(i)));
            try {
                spotRepository.save(spot);
            } catch (PersistenceException ex) {
                throw new DatabaseException("Error occurred", ex);
            }
            RouteSpotInfo spotInfo = new RouteSpotInfo();
            spotInfo.setStationTitle(spot.getStation().getTitle());
            spotInfo.setMinutesSinceDeparture(spot.getMinutesSinceDeparture());
            routeSpotInfos.add(spotInfo);
        }
        return routeSpotInfos;
    }

    private void checkFields(String title, LocalTime timeOfDeparture, List<Integer> stationIds, List<Integer> minutesSinceDepartures, BigDecimal pricePerMinute) {
        if (title == null || timeOfDeparture == null || stationIds == null || minutesSinceDepartures == null || pricePerMinute == null) {
            throw new BusinessLogicException("All fields are required");
        }
        if (pricePerMinute.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessLogicException("Invalid price");
        }
        if (stationIds.size() < 2) {
            throw new BusinessLogicException("Number of stations should be greater than or equal to 2");
        }
        for (Integer id : stationIds) {
            if (id == null) {
                throw new BusinessLogicException("All stations are required");
            }
        }
        for (int minute : minutesSinceDepartures) {
            if (minute < 0) {
                throw new BusinessLogicException("Invalid minutes since departures");
            }
        }
        if (minutesSinceDepartures.size() < stationIds.size()) {
            throw new BusinessLogicException("All minutes since departures are required");
        }
        Set minutes = new HashSet<>(minutesSinceDepartures);
        if (minutes.size() < minutesSinceDepartures.size()) {
            throw new BusinessLogicException("Minutes since departures must be different");
        }
        Set stations = new HashSet<>(stationIds);
        if (stations.size() < stationIds.size()) {
            throw new BusinessLogicException("Stations reiterate");
        }
        if (existsRoute(title)){
            throw new BusinessLogicException(String.format("Route %s already exists", title));
        }
    }

}
