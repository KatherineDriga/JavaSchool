package com.tsystems.javaschool.uberbahn.webmain.controllers;

import com.tsystems.javaschool.uberbahn.webmain.temp.services.ModelService;
import com.tsystems.javaschool.uberbahn.webmain.temp.services.ModelServiceImpl;
import com.tsystems.javaschool.uberbahn.webmain.temp.transports.ModelListItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

public class BaseControllerImpl extends HttpServlet {

    private static final SessionFactory SESSION_FACTORY;

    static {
        Configuration configuration = new Configuration().configure();
        SESSION_FACTORY = configuration.buildSessionFactory();
    }

    protected <R> R runTransaction(Function<Session, R> function) {
        Session session = null;
        try {
            session = SESSION_FACTORY.openSession();
            Transaction transaction = session.beginTransaction();

            R result = function.apply(session);

            transaction.commit();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    protected void render(String view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp").forward(req, resp);
    }
}
