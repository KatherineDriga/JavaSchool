package com.tsystems.javaschool.uberbahn.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "spot", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"routeId", "minutesSinceDeparture"}),
        @UniqueConstraint(columnNames = {"stationId", "routeId"})})
public class Spot extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "routeId", nullable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;


    @Column(name = "minutesSinceDeparture", nullable = false)
    private Integer minutesSinceDeparture;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "spot", fetch = FetchType.EAGER)
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




