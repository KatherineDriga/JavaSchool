package com.tsystems.javaschool.uberbahn.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "train", uniqueConstraints = @UniqueConstraint(columnNames = {"routeId", "dateOfDeparture"}))
public class Train extends BaseEntity {

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "train", fetch = FetchType.EAGER)
    private Collection<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "routeId", nullable = false)
    private Route route;

    @Column(name = "dateOfDeparture", nullable = false)
    private LocalDate dateOfDeparture;

    @Column(name = "numberOfSeats", nullable = false)
    private int numberOfSeats;

    @Column(name = "priceCoefficient", nullable = false)
    private double priceCoefficient;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "train", fetch = FetchType.EAGER)
    @OrderBy("instant ASC")
    private List<Presence> presences;

    @Column(name = "archived", nullable = false)
    private boolean archived;

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

    public double getPriceCoefficient() {
        return priceCoefficient;
    }

    public void setPriceCoefficient(double priceCoefficient) {
        this.priceCoefficient = priceCoefficient;
    }

    public List<Presence> getPresences() {
        return presences;
    }

    public void setPresences(List<Presence> presences) {
        this.presences = presences;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
