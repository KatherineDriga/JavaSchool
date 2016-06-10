package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Train;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collection;

public class TrainRepositoryImpl extends BaseRepositoryImpl implements BaseRepository, TrainRepository {

    public TrainRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Train findById(int id) {
        Train train = null;
        String hql = "FROM Train WHERE id = :id";
        Query query = getSession().createQuery(hql).setInteger("id", id);
        if (train == null){
            throw new RuntimeException();
        }
        return train;
    }

    @Override
    public void save(Object train) {
        getSession().saveOrUpdate(train);
    }

    @Override
    public void delete(int id) {
        String hql = "DELETE FROM Train WHERE id = :id";
        Query query = getSession().createQuery(hql).setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public Collection<Train> findByRoute(int routeId) {
        String hql = "FROM Train WHERE id = :routeId";
        Query query = getSession().createQuery(hql).setInteger("routeId", routeId);
        return query.list();
    }
}
