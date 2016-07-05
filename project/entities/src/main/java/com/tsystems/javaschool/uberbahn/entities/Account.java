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

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }
}
