package com.tsystems.javaschool.uberbahn.webmain.temp.services;

import com.tsystems.javaschool.uberbahn.webmain.services.BaseServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.temp.entities.Model;
import com.tsystems.javaschool.uberbahn.webmain.temp.repositories.ModelRepository;
import com.tsystems.javaschool.uberbahn.webmain.temp.repositories.ModelRepositoryImpl;
import com.tsystems.javaschool.uberbahn.webmain.temp.transports.ModelListItem;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ASUS on 09.06.2016.
 */
public class ModelServiceImpl extends BaseServiceImpl implements ModelService {

    public ModelServiceImpl(Session session) {
        super(session);
    }

    @Override
    public Collection<ModelListItem> getList() {
        ModelRepository repository = new ModelRepositoryImpl(getSession()); // TODO: with DI
        Collection<Model> models = repository.findAll();

        int number = 1;
        Collection<ModelListItem> result = new ArrayList<>();
        for (Model model : models) {
            ModelListItem item = new ModelListItem();
            item.setId(model.getId());
            item.setTitle(model.getTitle());
            item.setNumber(number++);

            result.add(item);
        }
        return result;
    }
}
