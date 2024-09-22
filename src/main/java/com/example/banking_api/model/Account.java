package com.example.banking_api.model;

import com.example.banking_api.dto.TransactionDTO;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private Long accountId;
    private double balance;
    private List<TransactionDTO> transactionHistory = new ArrayList<>();

    public Account(Long accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    // Getters and Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionDTO> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<TransactionDTO> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}