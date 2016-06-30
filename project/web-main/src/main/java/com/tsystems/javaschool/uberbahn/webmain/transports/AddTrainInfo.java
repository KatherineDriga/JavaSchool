package com.tsystems.javaschool.uberbahn.webmain.transports;


import java.time.LocalDate;

public class AddTrainInfo {

    private int id;
    private int routeId;
    private LocalDate dateOfDeparture;
    private int numberOfSeats;
    private double priceCoefficient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getPriceCoefficient() {
        return priceCoefficient;
    }

    public void setPriceCoefficient(double priceCoefficient) {
        this.priceCoefficient = priceCoefficient;
    }
}
