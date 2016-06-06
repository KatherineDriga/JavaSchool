package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "Route")
public class Route extends BaseEntity {


    @OneToMany (mappedBy = "route")
    private Collection<Train> trains;

    @OneToMany (mappedBy = "route")
    private Collection<Spot> spots;

    @Column(name = "title")
    private String title;

    @Column(name = "timeOfDeparture")
    private LocalTime timeOfDeparture;

}
