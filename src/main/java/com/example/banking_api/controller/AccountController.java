package com.example.banking_api.controller;

import com.example.banking_api.dto.AccountDTO;
import com.example.banking_api.dto.TransferRequestDTO;
import com.example.banking_api.dto.TransactionDTO;
import com.example.banking_api.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    // Constructor injection (Dependency Injection)
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Create a new account
    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestParam Long accountId, @RequestParam double initialBalance) {
        AccountDTO accountDTO = accountService.createAccount(accountId, initialBalance);
        return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
    }

    // Transfer funds between accounts
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequestDTO transferRequest) {
        String result = accountService.transferFunds(transferRequest);
        if (result.equals("Transfer successful.")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    // Get transaction history for a given account
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@PathVariable Long accountId) {
        List<TransactionDTO> transactionHistory = accountService.getTransactionHistory(accountId);
        if (transactionHistory != null) {
            return new ResponseEntity<>(transactionHistory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
