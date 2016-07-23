package com.tsystems.javaschool.uberbahn.services.unittests;


import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.repositories.AccountRepository;
import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.services.AccountServiceImpl;
import com.tsystems.javaschool.uberbahn.services.errors.BusinessLogicException;
import com.tsystems.javaschool.uberbahn.webmain.WebInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInitializer.class})
public class AccountServiceJUnitTest {


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
        String enteredLogin = "loginUser";
        System.out.println("Stubbing to create account with already existing loginUser");
        when(accountRepository.findByLogin(enteredLogin)).thenReturn(account);
        System.out.println("accountService.create should throw BusinessLogicException");
        accountService.create(enteredLogin, "email@example.com", "password", "firstName", "lastName", LocalDate.of(2000, 1, 10), false);
    }

    @Test(expected = BusinessLogicException.class)
    public void createAccountWithEmptyFields() {
        System.out.println("Stubbing to create account with unfilled fields");
        System.out.println("accountService.create should throw BusinessLogicException");
        accountService.create(null, "email@example.com", null, "firstName", "lastName", LocalDate.of(2000, 1, 10), false);

    }

    @Test(expected = BusinessLogicException.class)
    public void createAccountWithInvalidDateOfBirth() {
        LocalDate dateOfBirth = LocalDate.now().plusDays(1);
        System.out.println("Stubbing to create account with invalid date of birth");
        System.out.println("accountService.create should throw BusinessLogicException");
        accountService.create("loginUser", "email@example.com", "password", "firstName", "lastName", dateOfBirth, false);
    }

    @Test(expected = BusinessLogicException.class)
    public void createAccountWithInvalidEmail () {
        String enteredEmail = "email";
        System.out.println("Stubbing to create account with invalid email");
        System.out.println("accountService.create should throw BusinessLogicException");
        accountService.create("loginUser", enteredEmail, "password", "firstName", "lastName", LocalDate.of(2000, 1, 10), false);
    }

    @Test
    public void checkIfLoginAlreadyExists () {
        String enteredLogin = "loginUser";
        System.out.println("accountService.create should use accountRepository to find by loginUser");
        accountService.create(enteredLogin, "email@example.com", "password", "firstName", "lastName", LocalDate.of(2000, 1, 10), false);
        verify(accountRepository, times(1)).findByLogin(enteredLogin);
    }
}
