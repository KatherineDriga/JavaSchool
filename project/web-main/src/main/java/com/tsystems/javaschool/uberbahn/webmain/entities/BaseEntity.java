package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by ASUS on 06.06.2016.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
}
