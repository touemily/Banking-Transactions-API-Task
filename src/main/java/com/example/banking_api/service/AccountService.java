package com.example.banking_api.service;

import com.example.banking_api.dto.AccountDTO;
import com.example.banking_api.dto.TransactionDTO;
import com.example.banking_api.dto.TransferRequestDTO;
import com.example.banking_api.model.Account;
import com.example.banking_api.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // Constructor injection (Dependency Injection)
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Create a new account with initial balance
    public AccountDTO createAccount(Long accountId, double initialBalance) {
        Account account = accountRepository.createAccount(accountId, initialBalance);
        return mapToAccountDTO(account);
    }

    // Transfer funds between accounts
    public String transferFunds(TransferRequestDTO transferRequest) {
        Account fromAccount = accountRepository.findAccountById(transferRequest.getFromAccountId());
        Account toAccount = accountRepository.findAccountById(transferRequest.getToAccountId());

        if (fromAccount == null || toAccount == null) {
            return "Invalid account ID.";
        }

        if (fromAccount.getBalance() < transferRequest.getAmount()) {
            return "Insufficient funds.";
        }

        // Deduct from the sender account and add to the recipient account
        fromAccount.setBalance(fromAccount.getBalance() - transferRequest.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transferRequest.getAmount());

        // Create a transaction log
        TransactionDTO transaction = new TransactionDTO();
        transaction.setFromAccountId(transferRequest.getFromAccountId());
        transaction.setToAccountId(transferRequest.getToAccountId());
        transaction.setAmount(transferRequest.getAmount());
        transaction.setTimestamp(LocalDateTime.now());

        fromAccount.getTransactionHistory().add(transaction);
        toAccount.getTransactionHistory().add(transaction);

        return "Transfer successful.";
    }

    // Retrieve transaction history for a given account
    public List<TransactionDTO> getTransactionHistory(Long accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account != null) {
            return account.getTransactionHistory();
        } else {
            return null;
        }
    }

    // Helper method to convert Account entity to AccountDTO
    private AccountDTO mapToAccountDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountId(account.getAccountId());
        dto.setBalance(account.getBalance());
        return dto;
    }
}