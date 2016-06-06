package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "AppUser")
public class AppUser implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @OneToMany (mappedBy = "appUser")
    private Set tickets;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "employee")
    private boolean employee;

}
