package com.tsystems.javaschool.uberbahn.webmain.transports;


import java.time.LocalDate;
import java.time.LocalTime;

public class TrainDepartureTime {

    int trainId;
    String routeTitle;
    LocalDate dateOfDearture;
    LocalTime timeOfDeparture;
    LocalTime timeSinceDeparture;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getRouteTitle() {
        return routeTitle;
    }

    public void setRouteTitle(String routeTitle) {
        this.routeTitle = routeTitle;
    }

    public LocalDate getDateOfDearture() {
        return dateOfDearture;
    }

    public void setDateOfDearture(LocalDate dateOfDearture) {
        this.dateOfDearture = dateOfDearture;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public LocalTime getTimeSinceDeparture() {
        return timeSinceDeparture;
    }

    public void setTimeSinceDeparture(LocalTime timeSinceDeparture) {
        this.timeSinceDeparture = timeSinceDeparture;
    }
}
