package com.tsystems.javaschool.uberbahn.webmain.repositories;

import org.hibernate.Session;


public class BaseRepositoryImpl {

    private final Session session;

    public BaseRepositoryImpl(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return session;
    }
}
