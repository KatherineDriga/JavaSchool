package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Train")
public class Train implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @OneToMany (mappedBy = "train")
    private Set trains;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @Column(name = "dateOfDeparture")
    private LocalDate dateOfDeparture;

    @Column(name = "numberOfSeats")
    private int numberOfSeats;

}
