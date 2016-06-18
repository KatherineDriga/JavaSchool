package com.tsystems.javaschool.uberbahn.webmain.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaseControllerImpl extends HttpServlet {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    private static final SessionFactory SESSION_FACTORY;

    static {
        Configuration configuration = new Configuration().configure();
        SESSION_FACTORY = configuration.buildSessionFactory();
    }

    protected <R> R runTransaction(Function<Session, R> function) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SESSION_FACTORY.openSession();
            transaction = session.beginTransaction();

            R result = function.apply(session);

            transaction.commit();

            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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

    protected String getRequiredParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s missing", name));
        }
        return value;
    }

    protected int getIntParameter(String name, HttpServletRequest request) {
        return Integer.parseInt(getRequiredParameter(name, request));
    }

    protected LocalDateTime getDatetimeParameter(String name, HttpServletRequest request) {
        return LocalDateTime.parse(getRequiredParameter(name, request), FORMATTER);
    }

    protected LocalDate getDateParameter(String name, HttpServletRequest request) {
        return LocalDate.parse(getRequiredParameter(name, request), DATE_FORMATTER);
    }

    protected LocalTime getTimeParameter(String name, HttpServletRequest request) {
        return LocalTime.parse(getRequiredParameter(name, request), TIME_FORMATTER);
    }

    protected List<String> getArrayParameter(String name, HttpServletRequest request) {
        return Arrays.asList(getRequiredParameter(name, request).split(";"));
    }

    protected List<Integer> getIntArrayParameter(String name, HttpServletRequest request) {
/*
        List<Integer> result = new ArrayList<>();
        for(String i : getArrayParameter(name, request)) {
            result.add(Integer.parseInt(i));
        }
        return result;
*/
        return getArrayParameter(name, request)
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }

}
