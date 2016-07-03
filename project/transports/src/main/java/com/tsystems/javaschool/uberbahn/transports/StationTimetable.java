package com.tsystems.javaschool.uberbahn.transports;

import java.util.Collection;


public class StationTimetable {

    private String title;

    private Collection<StationScheduleEvent> scheduleEvents;

    public Collection<StationScheduleEvent> getScheduleEvents() {
        return scheduleEvents;
    }

    public void setScheduleEvents(Collection<StationScheduleEvent> scheduleEvents) {
        this.scheduleEvents = scheduleEvents;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
