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


    @Column(name = "timeSinceDeparture")
    private LocalTime timeSinceDeparture;

    public Route getRoute() {
        return route;
    }

    public Station getStation() {
        return station;
    }

    public LocalTime getTimeSinceDeparture() {
        return timeSinceDeparture;
    }
}




