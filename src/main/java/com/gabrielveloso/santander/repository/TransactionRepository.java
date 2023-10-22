package com.gabrielveloso.santander.repository;

import com.gabrielveloso.santander.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_AccountNumber(String accountNumber);
}
