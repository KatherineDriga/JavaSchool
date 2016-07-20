package com.tsystems.javaschool.uberbahn.services;


import com.tsystems.javaschool.uberbahn.transports.AccountDetails;

import java.time.LocalDate;

public interface AccountService {

    /**
     *Method creates new application user account, saves it in database and returns account details
     * @param login application user login
     * @param email application user email
     * @param password application user password (secret)
     * @param firstName application user first name
     * @param lastName application user last name
     * @param dateOfBirth application user date of birth
     * @param employee determines whether an application user is an employee or not
     * @return data transfer object containing registered account information
     */
    AccountDetails create (String login, String email, String password, String firstName, String lastName, LocalDate dateOfBirth, boolean employee);


    /**
     * Method gets account from database by application user login and returns account details
     * @param login application user login
     * @return data transfer object containing account information
     */
    AccountDetails getByLogin (String login);


    /**
     * Method gets account from database by its id and returns account details
     * @param id account id (primary key)
     * @return data transfer object containing account information
     */
    AccountDetails getById (int id);


    /**
     * Method checks whether account with such login already exists
     * @param login application user login
     * @return boolean value
     */
    boolean existsLogin (String login);


    /**
     * Method checks whether account with such email already exists
     * @param email application user
     * @return boolean value
     */
    boolean existsEmail (String email);
}
