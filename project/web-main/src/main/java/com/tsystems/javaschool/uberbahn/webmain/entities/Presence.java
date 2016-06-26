package com.tsystems.javaschool.uberbahn.webmain.entities;


import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime instant;

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

    public LocalDateTime getInstant() {
        return instant;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }
}
