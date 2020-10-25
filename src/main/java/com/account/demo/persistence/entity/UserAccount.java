package com.account.demo.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "user_account")
@Data
public class UserAccount {

    @Id
    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance_date")
    private LocalDate balanceDate;

    @Column(name = "currency")
    private String currency;

    @Column(name = "opening_balance")
    private Double openingBalance;
}
