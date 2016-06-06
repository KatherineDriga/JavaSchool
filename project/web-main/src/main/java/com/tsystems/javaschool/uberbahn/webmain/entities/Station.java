package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "station")
public class Station extends BaseEntity {


    @OneToMany (mappedBy = "station")
    private Collection<Spot> spots;

    @OneToMany (mappedBy = "stationOfDeparture")
    private Collection<Ticket> departures;

    @OneToMany (mappedBy = "stationOfArrival")
    private Collection<Ticket> arrivals;

    @Column(name = "title")
    private String title;

}
