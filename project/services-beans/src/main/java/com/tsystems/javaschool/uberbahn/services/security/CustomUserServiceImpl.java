package com.tsystems.javaschool.uberbahn.services.security;


import com.tsystems.javaschool.uberbahn.entities.Account;
import com.tsystems.javaschool.uberbahn.repositories.AccountRepository;
import com.tsystems.javaschool.uberbahn.transports.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class CustomUserServiceImpl implements UserDetailsService{

    private final AccountRepository accountRepository;

    @Autowired
    public CustomUserServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Account account = accountRepository.findByLogin(login);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", login));
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUserName(account.getLogin());
        userDetails.setPassword(account.getSecret());
        Collection<SimpleGrantedAuthority> userAuthorities = new ArrayList<>();
        SimpleGrantedAuthority clientAuthority = new SimpleGrantedAuthority("CLIENT");
        userAuthorities.add(clientAuthority);

        if (account.isEmployee()) {
            SimpleGrantedAuthority employeeAuthority = new SimpleGrantedAuthority("EMPLOYEE");
            userAuthorities.add(employeeAuthority);
        }
        userDetails.setAuthorities(userAuthorities);

        return userDetails;
    }
}
