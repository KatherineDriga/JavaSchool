package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Instant;

@Entity
@Table(name = "Ticket")
public class Ticket extends BaseEntity {


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

    @Column(name = "datetimeOfPurchase")
    private Instant datetimeOfPurchase;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

}
