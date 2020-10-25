package com.account.demo.persistence;

import com.account.demo.persistence.entity.AccountTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountTransactionsRepository extends CrudRepository<AccountTransaction, Integer> {

    List<AccountTransaction> findAllByAccountNumber(Integer accountNumber);
}
