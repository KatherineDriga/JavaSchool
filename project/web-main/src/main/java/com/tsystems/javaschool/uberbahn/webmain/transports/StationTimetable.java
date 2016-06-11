package com.tsystems.javaschool.uberbahn.webmain.transports;

import java.util.Collection;

/**
 * Created by ASUS on 11.06.2016.
 */
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
