package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train extends BaseEntity {


    @OneToMany (mappedBy = "train")
    private Collection<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @Column(name = "dateOfDeparture")
    private LocalDate dateOfDeparture;

    @Column(name = "numberOfSeats")
    private int numberOfSeats;

}
