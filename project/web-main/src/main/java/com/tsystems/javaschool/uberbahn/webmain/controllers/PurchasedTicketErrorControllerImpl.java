package com.tsystems.javaschool.uberbahn.webmain.controllers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchasedTicketErrorControllerImpl extends BaseControllerImpl {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = getRequiredParameter("message", req);

        req.setAttribute("message", message);

        render("purchasedTicketError", req, resp);
    }
}
