package com.tsystems.javaschool.uberbahn.services;

import org.hibernate.Session;


public class BaseServiceImpl {

    private final Session session;

    public BaseServiceImpl(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return session;
    }

}