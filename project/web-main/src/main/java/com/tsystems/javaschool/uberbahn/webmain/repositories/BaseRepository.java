package com.tsystems.javaschool.uberbahn.webmain.repositories;


public interface BaseRepository {

    Object findById(int id);
    void save(Object object);
    void delete(int id);
}
