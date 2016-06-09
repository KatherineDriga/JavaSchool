package com.tsystems.javaschool.uberbahn.webmain.temp.repositories;

import com.tsystems.javaschool.uberbahn.webmain.temp.entities.Model;

import java.util.Collection;

/**
 * Created by ASUS on 09.06.2016.
 */
public interface ModelRepository {

    Collection<Model> findAll();
}
