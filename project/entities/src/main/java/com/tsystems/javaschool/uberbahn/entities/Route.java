package com.tsystems.javaschool.uberbahn.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "route")
public class Route extends BaseEntity {


    @OneToMany (cascade = CascadeType.ALL, mappedBy = "route", fetch = FetchType.EAGER)
    private Collection<Train> trains;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "route", fetch = FetchType.EAGER)
    @OrderBy("minutesSinceDeparture ASC")
    private List<Spot> spots;

    @Column(name = "title")
    private String title;

    @Column(name = "timeOfDeparture")
    private LocalTime timeOfDeparture;

    @Column(name = "pricePerMinute")
    private BigDecimal pricePerMinute;

    public Collection<Train> getTrains() {
        return trains;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public String getTitle() {
        return title;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTrains(Collection<Train> trains) {
        this.trains = trains;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public BigDecimal getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(BigDecimal pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
}
