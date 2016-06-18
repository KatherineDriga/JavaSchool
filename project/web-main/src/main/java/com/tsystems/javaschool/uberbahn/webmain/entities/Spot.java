package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "spot")
public class Spot extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;


    @Column(name = "minutesSinceDeparture")
    private Integer minutesSinceDeparture;

    public Route getRoute() {
        return route;
    }

    public Station getStation() {
        return station;
    }

    public Integer getMinutesSinceDeparture() {
        return minutesSinceDeparture;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setMinutesSinceDeparture(Integer minutesSinceDeparture) {
        this.minutesSinceDeparture = minutesSinceDeparture;
    }
}




