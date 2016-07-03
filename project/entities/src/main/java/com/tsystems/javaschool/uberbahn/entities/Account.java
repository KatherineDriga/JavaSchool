package com.tsystems.javaschool.uberbahn.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<Ticket> tickets;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "secret")
    private String secret;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "employee")
    private boolean employee;

}
