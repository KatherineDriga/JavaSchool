package com.tsystems.javaschool.uberbahn.transports;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketInfo {

    private int id;
    private int trainId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String stationOfDeparture;
    private String stationOfArrival;
    private LocalDateTime datetimeOfPurchase;
    private LocalDateTime datetimeOfDeparture;
    private LocalDateTime datetimeOfArrival;
    private BigDecimal price;
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStationOfDeparture() {
        return stationOfDeparture;
    }

    public void setStationOfDeparture(String stationOfDeparture) {
        this.stationOfDeparture = stationOfDeparture;
    }

    public String getStationOfArrival() {
        return stationOfArrival;
    }

    public void setStationOfArrival(String stationOfArrival) {
        this.stationOfArrival = stationOfArrival;
    }

    public LocalDateTime getDatetimeOfPurchase() {
        return datetimeOfPurchase;
    }

    public void setDatetimeOfPurchase(LocalDateTime datetimeOfPurchase) {
        this.datetimeOfPurchase = datetimeOfPurchase;
    }

    public LocalDateTime getDatetimeOfDeparture() {
        return datetimeOfDeparture;
    }

    public void setDatetimeOfDeparture(LocalDateTime datetimeOfDeparture) {
        this.datetimeOfDeparture = datetimeOfDeparture;
    }

    public LocalDateTime getDatetimeOfArrival() {
        return datetimeOfArrival;
    }

    public void setDatetimeOfArrival(LocalDateTime datetimeOfArrival) {
        this.datetimeOfArrival = datetimeOfArrival;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

