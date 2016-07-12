package com.tsystems.javaschool.uberbahn.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket", uniqueConstraints = @UniqueConstraint(columnNames = {"trainId", "firstName", "lastName", "dateOfBirth"}))
public class Ticket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trainId", nullable = false)
    private Train train;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "stationOfDepartureId", nullable = false)
    private Station stationOfDeparture;

    @ManyToOne
    @JoinColumn(name = "stationOfArrivalId", nullable = false)
    private Station stationOfArrival;

    @Column(name = "datetimeOfPurchase", nullable = false)
    private LocalDateTime datetimeOfPurchase;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

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

    public LocalDateTime getDatetimeOfPurchase() {
        return datetimeOfPurchase;
    }

    public Account getAccount() {
        return account;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStationOfDeparture(Station stationOfDeparture) {
        this.stationOfDeparture = stationOfDeparture;
    }

    public void setStationOfArrival(Station stationOfArrival) {
        this.stationOfArrival = stationOfArrival;
    }

    public void setDatetimeOfPurchase(LocalDateTime datetimeOfPurchase) {
        this.datetimeOfPurchase = datetimeOfPurchase;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
