package com.tsystems.javaschool.uberbahn.webmain.repositories;


import com.tsystems.javaschool.uberbahn.webmain.entities.Account;
import org.hibernate.Session;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Account> implements AccountRepository {

    public AccountRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Account findById(int id) {
        return getSession().get(Account.class, id);
    }
}

