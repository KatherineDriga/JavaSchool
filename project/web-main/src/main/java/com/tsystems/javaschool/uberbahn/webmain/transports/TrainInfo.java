package com.tsystems.javaschool.uberbahn.webmain.transports;


import java.time.LocalDate;
import java.time.LocalTime;

public class TrainInfo {

    private String stationOfDeparture;
    private String stationOfArrival;
    private LocalDate dateSince;
    private LocalTime timeSince;
    private LocalDate dateUntil;
    private LocalTime timeUntil;

    public String getStationOfDeparture() {
        return stationOfDeparture;
    }

    public void setStationOfDeparture(String stationOfDeparture) {
        this.stationOfDeparture = stationOfDeparture;
    }

    public String getStationOfArrival() {
        return stationOfArrival;
    }

    public void setStationOfArrival(String stationOfArrival) {
        this.stationOfArrival = stationOfArrival;
    }

    public LocalDate getDateSince() {
        return dateSince;
    }

    public void setDateSince(LocalDate dateSince) {
        this.dateSince = dateSince;
    }

    public LocalTime getTimeSince() {
        return timeSince;
    }

    public void setTimeSince(LocalTime timeSince) {
        this.timeSince = timeSince;
    }

    public LocalDate getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(LocalDate dateUntil) {
        this.dateUntil = dateUntil;
    }

    public LocalTime getTimeUntil() {
        return timeUntil;
    }

    public void setTimeUntil(LocalTime timeUntil) {
        this.timeUntil = timeUntil;
    }
}
