package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
public class Ticket implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "trainId")
    private Train train;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "stationOfDepartureId")
    private Station stationOfDeparture;

    @ManyToOne
    @JoinColumn(name = "stationOfArrivalId")
    private Station stationOfArrival;

    @Column(name = "dateOfPurchase")
    private LocalDateTime dateOfPurchase;

    @ManyToOne
    @JoinColumn(name = "id")
    private AppUser appUser;

}
