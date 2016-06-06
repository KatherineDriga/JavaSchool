package com.tsystems.javaschool.uberbahn.webmain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Account")
public class Account extends BaseEntity {

    @OneToMany (mappedBy = "account")
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
