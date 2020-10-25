package com.account.demo.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "account_transaction")
@Data
public class AccountTransaction {

    @Id
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "value_date")
    private LocalDate valueDate;

    @Column(name = "currency")
    private String currency;

    @Column(name = "debit_amount")
    private Double debitAmount;

    @Column(name = "credit_amount")
    private Double creditAmount;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_narrative")
    private String transactionNarrative;

}
