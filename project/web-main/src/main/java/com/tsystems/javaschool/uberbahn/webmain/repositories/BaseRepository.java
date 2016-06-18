package com.tsystems.javaschool.uberbahn.webmain.repositories;


public interface BaseRepository<E> {

    E findById(int id);

    E save(E entity);

    void delete(int id);

    E update(E entity);
}
