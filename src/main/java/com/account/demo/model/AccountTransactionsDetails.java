package com.account.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AccountTransactionsDetails {
    private String accountNumber;
    private String accountName;
    private LocalDate valueDate;
    private String currency;
    private Double debitAmount;
    private Double creditAmount;
    private String transactionType;
    private String transactionNarrative;
}
