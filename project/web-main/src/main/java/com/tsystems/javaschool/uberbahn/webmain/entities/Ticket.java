package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Instant;

@Entity
@Table(name = "ticket")
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

    public Train getTrain() {
        return train;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Station getStationOfDeparture() {
        return stationOfDeparture;
    }

    public Station getStationOfArrival() {
        return stationOfArrival;
    }

    public Instant getDatetimeOfPurchase() {
        return datetimeOfPurchase;
    }

    public Account getAccount() {
        return account;
    }
}
