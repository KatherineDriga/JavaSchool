package com.tsystems.javaschool.uberbahn.services;

import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.repositories.AccountRepository;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.services.errors.DatabaseException;
import com.tsystems.javaschool.uberbahn.transports.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.regex.Pattern;

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

        checkFields(login, email, password, firstName, lastName, dateOfBirth);

        Account account = new Account();
        account.setLogin(login);
        account.setEmail(email);
        account.setSecret(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setDateOfBirth(dateOfBirth);
        account.setEmployee(employee);

        try {
            accountRepository.save(account);
        } catch (PersistenceException ex) {
            throw new DatabaseException("Error occurred", ex);
        }
        return getAccountDetails(account);
    }


    @Override
    public AccountDetails getByLogin(String login) {
        Account account = accountRepository.findByLogin(login);
        return getAccountDetails(account);
    }

    @Override
    public AccountDetails getById(int id) {
        Account account = accountRepository.findOne(id);
        return getAccountDetails(account);
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

    private AccountDetails getAccountDetails(Account account) {
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

    private boolean allLetters (String string) {
        return string.chars().allMatch(x -> Character.isLetter(x));
    }

    private void checkFields (String login, String email, String password, String firstName, String lastName, LocalDate dateOfBirth) {
        if (login == null || email == null || password == null || firstName == null || lastName == null || dateOfBirth == null) {
            throw new BusinessLogicException("All fields are required");
        }
        if (!allLetters(firstName)) {
            throw new BusinessLogicException("Invalid first name");
        }
        if (!allLetters(lastName)) {
            throw new BusinessLogicException("Invalid last name");
        }
        if (LocalDate.now().isBefore(dateOfBirth)) {
            throw new BusinessLogicException("Invalid Date of Birth");
        }
        if (!emailCheck(email)) {
            throw new BusinessLogicException("Invalid email");
        }
        if (existsLogin(login)) {
            throw new BusinessLogicException(String.format("Login %s already exists", login));
        }
        if (existsEmail(email)) {
            throw new BusinessLogicException(String.format("Email %s already exists", email));
        }
    }

    private boolean emailCheck (String email) {
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .find();
    }
}
