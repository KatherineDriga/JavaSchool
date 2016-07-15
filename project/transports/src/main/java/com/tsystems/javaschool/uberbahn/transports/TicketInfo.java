package com.tsystems.javaschool.uberbahn.transports;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TicketInfo {

    private int id;
    private int trainId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String stationOfDeparture;
    private String stationOfArrival;
    private LocalDate dateOfDeparture;
    private LocalTime timeOfDeparture;
    private LocalDate dateOfArrival;
    private LocalTime timeOfArrival;
    private LocalDate dateOfPurchase;
    private LocalTime timeOfPurchase;
    private BigDecimal price;
    private String login;
    private String routeTitle;

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

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public LocalTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public LocalTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(LocalTime timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
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

    public String getRouteTitle() {
        return routeTitle;
    }

    public void setRouteTitle(String routeTitle) {
        this.routeTitle = routeTitle;
    }
}

