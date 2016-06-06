package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "Spot")
public class Spot extends BaseEntity {


    @Id
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @Column(name = "timeSinceDeparture")
    private LocalTime timeSinceDeparture;

}




