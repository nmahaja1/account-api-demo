package com.account.demo.service;

import com.account.demo.model.AccountDetails;
import com.account.demo.model.AccountTransactionsDetails;
import com.account.demo.persistence.AccountTransactionsRepository;
import com.account.demo.persistence.UserAccountRepository;
import com.account.demo.persistence.entity.AccountTransaction;
import com.account.demo.persistence.entity.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    UserAccountRepository userAccountRepository;

    @Mock
    AccountTransactionsRepository accountTransactionsRepository;

    @Test
    public void whenAccountsPresentInDB_thenGetAccountDetailsReturnsOK() {
        UserAccount userAccount1 = new UserAccount();
        userAccount1.setAccountName("Test 1");
        userAccount1.setAccountNumber(123);
        userAccount1.setAccountType("Credit");
        userAccount1.setCurrency("AUD");
        userAccount1.setBalanceDate(LocalDate.now());
        userAccount1.setOpeningBalance(1000.50);

        UserAccount userAccount2 = new UserAccount();
        userAccount2.setAccountName("Test 2");
        userAccount2.setAccountNumber(345);
        userAccount2.setAccountType("Credit");
        userAccount2.setCurrency("SGD");
        userAccount2.setBalanceDate(LocalDate.now());
        userAccount2.setOpeningBalance(500.24);

        Iterable<UserAccount> resultIter = Arrays.asList(userAccount1, userAccount2);
        Mockito.when(userAccountRepository.findAll()).thenReturn(resultIter);

        List<AccountDetails> result = accountService.getAccountDetails();
        assertNotNull(result, "Result should be returned");

        assertEquals("Test 1", result.get(0).getAccountName());
        assertEquals("Test 2", result.get(1).getAccountName());

        assertEquals("123", result.get(0).getAccountNumber());
        assertEquals("345", result.get(1).getAccountNumber());

        assertEquals("Credit", result.get(0).getAccountType());
        assertEquals("Credit", result.get(1).getAccountType());

        assertEquals("AUD", result.get(0).getCurrency());
        assertEquals("SGD", result.get(1).getCurrency());

        assertEquals(1000.50, result.get(0).getOpeningBalance(), 0.00000000001d);
        assertEquals(500.24, result.get(1).getOpeningBalance(), 0.00000000001d);
    }

    @Test
    public void whenNoAccountsInDB_thenGetAccountDetailsReturnsEmptyList() {
        Mockito.when(userAccountRepository.findAll()).thenReturn(Collections.emptyList());
        List<AccountDetails> result = accountService.getAccountDetails();
        assertNotNull(result, "Result should be returned");
        assertEquals(0, result.size());
    }

    @Test
    public void whenAccountSelected_thenGetAccountTransactionsReturnsOK() {
        AccountTransaction transaction1 = new AccountTransaction();
        transaction1.setAccountNumber(123);
        transaction1.setAccountName("Test 1");
        transaction1.setCreditAmount(100.23);
        transaction1.setCurrency("AUD");
        transaction1.setTransactionId(1);
        transaction1.setTransactionType("Credit");
        transaction1.setTransactionNarrative("Credit Txn 1");
        transaction1.setValueDate(LocalDate.now());

        AccountTransaction transaction2 = new AccountTransaction();
        transaction2.setAccountNumber(123);
        transaction2.setAccountName("Test 1");
        transaction2.setDebitAmount(250.66);
        transaction2.setCurrency("AUD");
        transaction2.setTransactionId(2);
        transaction2.setTransactionType("Debit");
        transaction2.setTransactionNarrative("Debit Txn 1");
        transaction2.setValueDate(LocalDate.now());

        List<AccountTransaction> resultIter = Arrays.asList(transaction1, transaction2);
        Mockito.when(accountTransactionsRepository.findAllByAccountNumber(Mockito.anyInt())).thenReturn(resultIter);

        List<AccountTransactionsDetails> result = accountService.getAccountTransactions("123");
        assertNotNull(result, "Result should be returned");
        assertEquals(2, result.size());

        assertEquals("123", result.get(0).getAccountNumber());
        assertEquals("123", result.get(1).getAccountNumber());

        assertEquals("Test 1", result.get(0).getAccountName());
        assertEquals("Test 1", result.get(1).getAccountName());

        assertEquals(100.23, result.get(0).getCreditAmount(), 0.00000000001d);
        assertEquals(250.66, result.get(1).getDebitAmount(), 0.00000000001d);

        assertEquals("AUD", result.get(0).getCurrency());
        assertEquals("AUD", result.get(1).getCurrency());

        assertEquals("Credit", result.get(0).getTransactionType());
        assertEquals("Debit", result.get(1).getTransactionType());

        assertEquals("Credit Txn 1", result.get(0).getTransactionNarrative());
        assertEquals("Debit Txn 1", result.get(1).getTransactionNarrative());
    }

    @Test
    public void whenNoAccountsTransactionsInDB_thenGetAccountTransactionsReturnsEmptyList() {
        Mockito.when(accountTransactionsRepository.findAllByAccountNumber(Mockito.anyInt())).thenReturn(Collections.emptyList());
        List<AccountTransactionsDetails> result = accountService.getAccountTransactions("1");
        assertNotNull(result, "Result should be returned");
        assertEquals(0, result.size());
    }

    @Test
    public void whenAccountNumberIsNull_thenGetAccountTransactionsThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.getAccountTransactions(null));
    }

    @Test
    public void whenAccountNumberIsNotNumeric_thenGetAccountTransactionsThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.getAccountTransactions("abc"));
    }
}
