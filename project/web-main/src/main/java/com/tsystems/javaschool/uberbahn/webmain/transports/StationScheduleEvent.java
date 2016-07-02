package com.tsystems.javaschool.uberbahn.webmain.transports;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public class StationScheduleEvent {

    private LocalDate date;
    private LocalTime time;
    private String route;
    private String departsFrom;
    private String arrivesAt;
    private int train;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDepartsFrom() {
        return departsFrom;
    }

    public void setDepartsFrom(String departsFrom) {
        this.departsFrom = departsFrom;
    }

    public String getArrivesAt() {
        return arrivesAt;
    }

    public void setArrivesAt(String arrivesAt) {
        this.arrivesAt = arrivesAt;
    }

    public int getTrain() {
        return train;
    }

    public void setTrain(int train) {
        this.train = train;
    }

}
