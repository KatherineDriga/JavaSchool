package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.AccountDetails;

import java.time.LocalDate;

public interface AccountService {

    AccountDetails create (String login, String email, String password, String firstName, String lastName, LocalDate dateOfBirth, boolean employee);

    AccountDetails getByLogin (String login);

    AccountDetails getById (int id);

    boolean existsLogin (String login);

    boolean existsEmail (String email);
}
