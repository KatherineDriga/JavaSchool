package com.tsystems.javaschool.uberbahn.webmain.errors;


import com.tsystems.javaschool.uberbahn.webmain.controllers.TrainTimetableSearchControllerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class ExceptionAdvice {
    private final Logger logger = LogManager.getLogger(TrainTimetableSearchControllerImpl.class);


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex) {
        logger.error("ERROR", ex);
        return "unknown error";
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseBody
    public String handleBusinessLogicException(BusinessLogicException ex) {
        logger.warn("WARN", ex);
        return ex.getMessage();
    }

}
