package com.gabrielveloso.santander.repository;

import com.gabrielveloso.santander.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
