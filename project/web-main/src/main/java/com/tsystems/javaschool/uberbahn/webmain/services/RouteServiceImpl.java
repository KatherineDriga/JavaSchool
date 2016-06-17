package com.tsystems.javaschool.uberbahn.webmain.services;


import com.tsystems.javaschool.uberbahn.webmain.entities.Route;
import com.tsystems.javaschool.uberbahn.webmain.repositories.RouteRepository;
import com.tsystems.javaschool.uberbahn.webmain.repositories.RouteRepositoryImpl;
import com.tsystems.javaschool.uberbahn.webmain.transports.RouteInfo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;

public class RouteServiceImpl extends BaseServiceImpl implements RouteService {

    private final RouteRepository routeRepository;


    public RouteServiceImpl(Session session) {
        super(session);
        this.routeRepository = new RouteRepositoryImpl(session);
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


}
