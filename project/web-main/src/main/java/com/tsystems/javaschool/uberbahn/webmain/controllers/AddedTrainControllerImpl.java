package com.tsystems.javaschool.uberbahn.webmain.controllers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddedTrainControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int trainId = getIntParameter("trainId", req);
        String message = getRequiredParameter("message", req);


        req.setAttribute("trainId", trainId);
        req.setAttribute("message", message);

        render("addedTrain", req, resp);
    }
}
