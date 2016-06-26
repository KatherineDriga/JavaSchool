package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Table(name = "spot")
public class Spot extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;


    @Column(name = "minutesSinceDeparture")
    private Integer minutesSinceDeparture;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "spot")
    private Collection<Presence> presences;

    public Route getRoute() {
        return route;
    }

    public Station getStation() {
        return station;
    }

    public Integer getMinutesSinceDeparture() {
        return minutesSinceDeparture;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setMinutesSinceDeparture(Integer minutesSinceDeparture) {
        this.minutesSinceDeparture = minutesSinceDeparture;
    }

    public Collection<Presence> getPresences() {
        return presences;
    }

    public void setPresences(Collection<Presence> presences) {
        this.presences = presences;
    }
}




