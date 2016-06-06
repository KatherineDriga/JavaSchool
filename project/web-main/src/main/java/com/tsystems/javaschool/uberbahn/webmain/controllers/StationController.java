package com.tsystems.javaschool.uberbahn.webmain.controllers;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by ASUS on 06.06.2016.
 */
public class StationController extends HttpServlet {

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
            String sql = "FROM Station";
            Query query=session.createQuery(sql);
            List res=query.list();
            transaction.commit();
            System.out.println("++++++");


        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            System.out.println("2222222");

        } finally {
            System.out.println("111111");

        }


    }


}
