package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "route")
public class Route extends BaseEntity {


    @OneToMany (mappedBy = "route")
    private Collection<Train> trains;

    @OneToMany (mappedBy = "route")
    @OrderBy("minutesSinceDeparture ASC")
    private List<Spot> spots;

    @Column(name = "title")
    private String title;

    @Column(name = "timeOfDeparture")
    private LocalTime timeOfDeparture;

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
}
