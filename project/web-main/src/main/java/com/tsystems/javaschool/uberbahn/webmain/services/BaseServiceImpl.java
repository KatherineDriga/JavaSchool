package com.tsystems.javaschool.uberbahn.webmain.services;

import org.hibernate.Session;

/**
 * Created by ASUS on 09.06.2016.
 */
public class BaseServiceImpl {

    private final Session session;

    public BaseServiceImpl(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return session;
    }

    /**
     * Created by ASUS on 09.06.2016.
     */
    public static interface StationService {
    }
}