package com.tsystems.javaschool.uberbahn.webmain.repositories;


public interface BaseRepository<E> {

    E findById(int id);

    int save(E entity);

    void delete(int id);

    void update(E entity);
}
