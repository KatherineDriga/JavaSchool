package com.tsystems.javaschool.uberbahn.webmain.temp.controllers;

import com.tsystems.javaschool.uberbahn.webmain.controllers.BaseControllerImpl;
import com.tsystems.javaschool.uberbahn.webmain.temp.services.ModelService;
import com.tsystems.javaschool.uberbahn.webmain.temp.services.ModelServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.temp.transports.ModelListItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ModelListControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<ModelListItem> models = runTransaction((session -> {
            ModelService service = new ModelServiceImpl(session); // TODO: with DI
            return service.getList();
        }));

        req.setAttribute("models", models);

        render("modelList", req, resp);
    }
}
