package com.account.demo.controller;

import com.account.demo.model.AccountDetails;
import com.account.demo.model.AccountTransactionsDetails;
import com.account.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDetails>> getUserAccounts() {
        List<AccountDetails> result = accountService.getAccountDetails();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{accountNumber}/transactions")
    public ResponseEntity<List<AccountTransactionsDetails>> getAccountTransactions(@PathVariable String accountNumber) {
        List<AccountTransactionsDetails> result= accountService.getAccountTransactions(accountNumber);
        return ResponseEntity.ok(result);
    }
}
