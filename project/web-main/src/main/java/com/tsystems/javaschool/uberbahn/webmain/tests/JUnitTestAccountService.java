package com.tsystems.javaschool.uberbahn.webmain.tests;


import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.repositories.AccountRepository;
import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.services.AccountServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.webmain.WebInizializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInizializer.class})
public class JUnitTestAccountService {


    private AccountRepository accountRepository;
    private AccountService accountService;
    private Account account;

    @Before
    public void setupMock() {
        account = mock(Account.class);
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test(expected = BusinessLogicException.class)
    public void createAccountWithExistingLogin () {
        String enteredLogin = "login";
        System.out.println("Stubbing to create account with already existing login");
        when(accountRepository.findByLogin(enteredLogin)).thenReturn(account);
        System.out.println("accountService.create should throw BusinessLogicException");
        accountService.create(enteredLogin, "email", "password", "firstName", "lastName", LocalDate.of(2000, 1, 10), false);
    }

    @Test(expected = PersistenceException.class)
    public void databaseWritingError() {
        System.out.println("Stubbing to throw PersistenceException");
        when(accountRepository.save(account)).thenReturn(null);
        System.out.println("accountService.create should throw PersistenceException");
        accountService.create("login", "email", "password", "firstName", "lastName", LocalDate.of(2000, 1, 10), false);
    }
}
