package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;


@Entity
@Table(name = "Route")
public class Route implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @OneToMany (mappedBy = "route")
    private Set trains;

    @OneToMany (mappedBy = "route")
    private Set spots;

    @Column(name = "title")
    private String title;

    @Column(name = "timeOfDeparture")
    private LocalTime timeOfDeparture;

}
