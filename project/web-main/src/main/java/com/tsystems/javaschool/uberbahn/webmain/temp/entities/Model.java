package com.tsystems.javaschool.uberbahn.webmain.temp.entities;

import com.tsystems.javaschool.uberbahn.webmain.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Model extends BaseEntity {

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
