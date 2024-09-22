package com.example.banking_api.repository;

import com.example.banking_api.model.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepository {

    private final Map<Long, Account> accounts = new HashMap<>();

    public Account createAccount(Long accountId, double initialBalance) {
        Account account = new Account(accountId, initialBalance);
        accounts.put(accountId, account);
        return account;
    }

    public Account findAccountById(Long accountId) {
        return accounts.get(accountId);
    }

    public Map<Long, Account> getAccounts() {
        return accounts;
    }
}