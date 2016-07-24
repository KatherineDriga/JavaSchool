package com.tsystems.javaschool.uberbahn.repositories;


import com.tsystems.javaschool.uberbahn.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Get account entity by its login
     * @param login account login
     * @return account entity
     */
    @Transactional(readOnly = true)
    Account findByLogin (String login);

    /**
     * Get account entity by its email
     * @param email account email
     * @return account entity
     */
    @Transactional(readOnly = true)
    Account findByEmail (String email);
}
