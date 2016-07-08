package com.tsystems.javaschool.uberbahn.webmain.exception;


import com.tsystems.javaschool.uberbahn.webmain.controllers.TrainTimetableSearchControllerImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler(PersistenceException.class)
    @ResponseBody
    public String handlePersistenceException(PersistenceException ex) {
        Logger logger = Logger.getLogger(TrainTimetableSearchControllerImpl.class);
        logger.warn("WARN", ex);
        return ex.getMessage();
    }

}
