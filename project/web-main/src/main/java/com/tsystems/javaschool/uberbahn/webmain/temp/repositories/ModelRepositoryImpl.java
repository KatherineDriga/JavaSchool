package com.tsystems.javaschool.uberbahn.webmain.temp.repositories;

import com.tsystems.javaschool.uberbahn.webmain.repositories.BaseRepositoryImpl;
import com.tsystems.javaschool.uberbahn.webmain.temp.entities.Model;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collection;

/**
 * Created by ASUS on 09.06.2016.
 */
public class ModelRepositoryImpl extends BaseRepositoryImpl implements ModelRepository {

    public ModelRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Collection<Model> findAll() {
        String hql = "FROM Model";
        Query query = getSession().createQuery(hql);
        return query.list();
    }
}
