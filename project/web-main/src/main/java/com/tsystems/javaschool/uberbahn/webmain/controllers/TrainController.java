package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.webmain.entities.Station;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 06.06.2016.
 */
public class TrainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();

            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            // 3. Get Session object
            Session session = sessionFactory.openSession();

            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();

            String stationTitle = req.getParameter("station");
            String sinceText = req.getParameter("since");
            String untilText = req.getParameter("until");

            String sql = "FROM Route AS r WHERE r.id=1";
            Query query = session.createQuery(sql);
            List<Station> res = query.list();
            req.setAttribute("stationList", res);
            req.getRequestDispatcher("/WEB-INF/views/listOfStations.jsp").forward(req, resp);

            transaction.commit();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }


}

