package com.account.demo.controller;

import com.account.demo.model.AccountDetails;
import com.account.demo.model.AccountTransactionsDetails;
import com.account.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AccountController.class, AccountService.class})
@WebMvcTest
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenUserAccountsExist_testGetUserAccountsReturnsOK() throws Exception {
        AccountDetails account1 = new AccountDetails();
        account1.setAccountNumber("1");
        account1.setAccountName("Test 1");
        account1.setOpeningBalance(100.50);
        account1.setCurrency("AUD");
        account1.setAccountType("Credit");
        List<AccountDetails> accountDetailsList = Arrays.asList(account1);
        Mockito.when(accountService.getAccountDetails()).thenReturn(accountDetailsList);
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().json("[{\"accountNumber\":\"1\",\"accountName\":\"Test 1\",\"accountType\":\"Credit\",\"balanceDate\":null,\"currency\":\"AUD\",\"openingBalance\":100.5}]"));
    }

    @Test
    public void whenAccountHasTransactions_testGetAccountTransactionsReturnsOK() throws Exception {
        AccountTransactionsDetails transaction1 = new AccountTransactionsDetails();
        transaction1.setAccountNumber("1");
        transaction1.setAccountName("Test 1");
        transaction1.setCreditAmount(100.05);
        transaction1.setCurrency("AUD");
        transaction1.setTransactionType("Credit");
        transaction1.setTransactionNarrative("Test Txn1");
        List<AccountTransactionsDetails> accountTransactions = Arrays.asList(transaction1);
        Mockito.when(accountService.getAccountTransactions(anyString())).thenReturn(accountTransactions);
        mockMvc.perform(get("/accounts/{accountNumber}/transactions", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().json("[{\"accountNumber\":\"1\",\"accountName\":\"Test 1\",\"valueDate\":null,\"currency\":\"AUD\",\"debitAmount\":null,\"creditAmount\":100.05,\"transactionType\":\"Credit\",\"transactionNarrative\":\"Test Txn1\"}]"));
    }
}
