package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train extends BaseEntity {


    @OneToMany (cascade = CascadeType.ALL, mappedBy = "train")
    private Collection<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @Column(name = "dateOfDeparture")
    private LocalDate dateOfDeparture;

    @Column(name = "numberOfSeats")
    private int numberOfSeats;

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public Route getRoute() {
        return route;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
