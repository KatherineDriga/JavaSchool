package com.tsystems.javaschool.uberbahn.entities;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "presence")
public class Presence extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trainId")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "spotId")
    private Spot spot;

    @Column (name = "instant")
    private Instant instant;

    @Column (name = "numberOfTicketsPurchased")
    private int numberOfTicketsPurchased;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public int getNumberOfTicketsPurchased() {
        return numberOfTicketsPurchased;
    }

    public void setNumberOfTicketsPurchased(int numberOfTicketsPurchased) {
        this.numberOfTicketsPurchased = numberOfTicketsPurchased;
    }
}
