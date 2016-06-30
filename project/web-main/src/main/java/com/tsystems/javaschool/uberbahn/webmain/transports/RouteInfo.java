package com.tsystems.javaschool.uberbahn.webmain.transports;


import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collection;

public class RouteInfo {

    private int id;
    private String title;
    private LocalTime timeOfDeparture;
    private Collection<RouteSpotInfo> spots;
    BigDecimal pricePerMinute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public Collection<RouteSpotInfo> getSpots() {
        return spots;
    }

    public void setSpots(Collection<RouteSpotInfo> spots) {
        this.spots = spots;
    }

    public BigDecimal getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(BigDecimal pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
}
