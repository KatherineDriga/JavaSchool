package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "Spot")
public class Spot implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;

    @Id
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @Column(name = "timeFromDeparture")
    private LocalTime timeFromDeparture;

}




