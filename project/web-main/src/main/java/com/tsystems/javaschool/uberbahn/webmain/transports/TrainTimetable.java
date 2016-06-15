package com.tsystems.javaschool.uberbahn.webmain.transports;


import java.util.Collection;

public class TrainTimetable {

    private String stationOfDeparture;
    private String stationOfArrival;
    private Collection<TrainScheduleEvent> scheduleEvents;

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

    public Collection<TrainScheduleEvent> getScheduleEvents() {
        return scheduleEvents;
    }

    public void setScheduleEvents(Collection<TrainScheduleEvent> scheduleEvents) {
        this.scheduleEvents = scheduleEvents;
    }
}
