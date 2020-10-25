package com.account.demo.persistence;

import com.account.demo.persistence.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {
}
