package com.tsystems.javaschool.uberbahn.transports;


public class RouteSpotInfo {
    private String stationTitle;
    private int minutesSinceDeparture;

    public String getStationTitle() {
        return stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }

    public int getMinutesSinceDeparture() {
        return minutesSinceDeparture;
    }

    public void setMinutesSinceDeparture(int minutesSinceDeparture) {
        this.minutesSinceDeparture = minutesSinceDeparture;
    }
}
