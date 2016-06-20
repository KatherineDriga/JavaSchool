package com.tsystems.javaschool.uberbahn.webmain.repositories;

import org.hibernate.Session;


public abstract class BaseRepositoryImpl<E> implements BaseRepository<E> {

    private final Session session;

    public BaseRepositoryImpl(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return session;
    }

    @Override
    public E save(E entity) {
        int id = (int) session.save(entity);
        //session.flush();
        return findById(id);
    }

    @Override
    public void delete(int id) {
        session.delete(findById(id));
        //session.flush();
    }

    @Override
    public E update(E entity) {
        session.update(entity);
        //session.flush();
        return entity;
    }
}
