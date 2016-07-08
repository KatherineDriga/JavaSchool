package com.tsystems.javaschool.uberbahn.services;

import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.repositories.AccountRepository;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl (AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDetails create(String login, String email, String password, String firstName, String lastName, LocalDate dateOfBirth, boolean employee) {
        Account account = new Account();
        account.setLogin(login);
        account.setEmail(email);
        account.setSecret(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setDateOfBirth(dateOfBirth);
        account.setEmployee(employee);

        int accountId = accountRepository.save(account).getId();

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(accountId);
        accountDetails.setLogin(login);
        accountDetails.setEmail(email);
        accountDetails.setSecret(password);
        accountDetails.setFirstName(firstName);
        accountDetails.setLastName(lastName);
        accountDetails.setDateOfBirth(dateOfBirth);
        accountDetails.setEmployee(employee);

        return accountDetails;
    }


    @Override
    public AccountDetails getByLogin(String login) {
        Account account = accountRepository.findByLogin(login);
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(account.getId());
        accountDetails.setLogin(account.getLogin());
        accountDetails.setEmail(account.getEmail());
        accountDetails.setSecret(account.getSecret());
        accountDetails.setFirstName(account.getFirstName());
        accountDetails.setLastName(account.getLastName());
        accountDetails.setDateOfBirth(account.getDateOfBirth());
        accountDetails.setEmployee(account.isEmployee());
        return accountDetails;
    }

    @Override
    public AccountDetails getById(int id) {
        Account account = accountRepository.findOne(id);
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(account.getId());
        accountDetails.setLogin(account.getLogin());
        accountDetails.setEmail(account.getEmail());
        accountDetails.setSecret(account.getSecret());
        accountDetails.setFirstName(account.getFirstName());
        accountDetails.setLastName(account.getLastName());
        accountDetails.setDateOfBirth(account.getDateOfBirth());
        accountDetails.setEmployee(account.isEmployee());
        return accountDetails;
    }

    @Override
    public boolean existsLogin(String login) {
        if (accountRepository.findByLogin(login) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existsEmail(String email) {
        if (accountRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }
}
