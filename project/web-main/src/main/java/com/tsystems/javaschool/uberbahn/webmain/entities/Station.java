package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Station")
public class Station implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @OneToMany (mappedBy = "station")
    private Set spots;

    @OneToMany (mappedBy = "stationOfDeparture")
    private Set stationsOfDeparture;

    @OneToMany (mappedBy = "stationOfArrival")
    private Set stationsOfArrival;

    @Column(name = "title")
    private String title;

}
