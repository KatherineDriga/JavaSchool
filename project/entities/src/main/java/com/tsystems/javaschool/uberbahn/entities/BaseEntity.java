package com.tsystems.javaschool.uberbahn.entities;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }
}
