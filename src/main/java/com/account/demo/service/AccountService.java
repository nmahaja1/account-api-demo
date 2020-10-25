package com.account.demo.service;

import com.account.demo.model.AccountDetails;
import com.account.demo.model.AccountTransactionsDetails;
import com.account.demo.persistence.AccountTransactionsRepository;
import com.account.demo.persistence.UserAccountRepository;
import com.account.demo.persistence.entity.AccountTransaction;
import com.account.demo.persistence.entity.UserAccount;
import com.account.demo.util.NumberUtil;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccountService {

    private UserAccountRepository userAccountRepository;
    private AccountTransactionsRepository accountTransactionsRepository;

    @Autowired
    public AccountService(UserAccountRepository userAccountRepository, AccountTransactionsRepository accountTransactionsRepository1) {
        this.userAccountRepository = userAccountRepository;
        this.accountTransactionsRepository = accountTransactionsRepository1;
    }

    /**
     * Fetches account details from the database
     * @return List of {@link AccountDetails}
     */
    public List<AccountDetails> getAccountDetails() {
        Iterable<UserAccount> records = userAccountRepository.findAll();
        return StreamSupport.stream(records.spliterator(), false)
                .map(userAccount -> createAccountDetails(userAccount))
                .collect(Collectors.toList());
    }

    /**
     * Finds transactions for an account number
     * @param accountNumber
     * @return List of {@link AccountTransactionsDetails}
     * @throws IllegalArgumentException if the account number is null or non-numeric
     */
    public List<AccountTransactionsDetails> getAccountTransactions(String accountNumber) {
        Preconditions.checkArgument(accountNumber != null, "Account Number is Mandatory Field");
        Preconditions.checkArgument(NumberUtil.isNumeric(accountNumber), "Account Number should be numeric");
        List<AccountTransactionsDetails> result = new ArrayList<>();
        List<AccountTransaction> records = accountTransactionsRepository.findAllByAccountNumber(Integer.parseInt(accountNumber));
        records.stream()
                .map(accountTransaction -> createTransactionDetails(accountTransaction))
                .collect(Collectors.toList());
        Iterator<AccountTransaction> iterator = records.iterator();
        while (iterator.hasNext()){
            result.add(createTransactionDetails(iterator.next()));
        }
        return result;
    }

    private AccountTransactionsDetails createTransactionDetails(AccountTransaction accountTransaction) {
        AccountTransactionsDetails transactionDetails = new AccountTransactionsDetails();
        transactionDetails.setAccountName(accountTransaction.getAccountName());
        transactionDetails.setAccountNumber(accountTransaction.getAccountNumber().toString());
        transactionDetails.setValueDate(accountTransaction.getValueDate());
        transactionDetails.setCurrency(accountTransaction.getCurrency());
        transactionDetails.setDebitAmount(accountTransaction.getDebitAmount());
        transactionDetails.setCreditAmount(accountTransaction.getCreditAmount());
        transactionDetails.setTransactionType(accountTransaction.getTransactionType());
        transactionDetails.setTransactionNarrative(accountTransaction.getTransactionNarrative());
        return transactionDetails;
    }

    private AccountDetails createAccountDetails(UserAccount userAccount) {
        AccountDetails details = new AccountDetails();
        details.setAccountNumber(userAccount.getAccountNumber().toString());
        details.setAccountName(userAccount.getAccountName());
        details.setAccountType(userAccount.getAccountType());
        details.setBalanceDate(userAccount.getBalanceDate());
        details.setCurrency(userAccount.getCurrency());
        details.setOpeningBalance(userAccount.getOpeningBalance());
        return details;
    }
}
