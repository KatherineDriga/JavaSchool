package com.tsystems.javaschool.uberbahn.repositories;


import com.tsystems.javaschool.uberbahn.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByLogin (String login);
}
