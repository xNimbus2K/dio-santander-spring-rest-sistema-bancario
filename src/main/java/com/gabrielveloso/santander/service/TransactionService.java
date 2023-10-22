package com.gabrielveloso.santander.service;

import com.gabrielveloso.santander.dto.TransactionDTO;
import com.gabrielveloso.santander.model.Account;
import com.gabrielveloso.santander.model.Transaction;
import com.gabrielveloso.santander.repository.AccountRepository;
import com.gabrielveloso.santander.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transfer(TransactionDTO transactionDTO) {
        String sourceAccountNumber = transactionDTO.getSourceAccount();
        String targetAccountNumber = transactionDTO.getTargetAccount();
        double amount = transactionDTO.getAmount();

        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber);
        Account targetAccount = accountRepository.findByAccountNumber(targetAccountNumber);

        if (sourceAccount == null || targetAccount == null) {
            throw new RuntimeException("One or both accounts do not exist.");
        }

        if (sourceAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds in the source account.");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

    }

    public double getBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account does not exist.");
        }
        return account.getBalance();
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionRepository.findByAccount_AccountNumber(accountNumber);
    }

    @Transactional
    public void deposit(String accountNumber, double amount, String description) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account does not exist.");
        }

        if (amount <= 0) {
            throw new RuntimeException("Invalid deposit amount.");
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction depositTransaction = new Transaction();
        depositTransaction.setAccount(account);
        depositTransaction.setTimestamp(new Date());
        depositTransaction.setAmount(amount);
        depositTransaction.setDescription(description);

        transactionRepository.save(depositTransaction);
    }

    @Transactional
    public void withdraw(String accountNumber, double amount, String description) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account does not exist.");
        }

        if (amount <= 0 || account.getBalance() < amount) {
            throw new RuntimeException("Invalid withdrawal amount or insufficient funds.");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction withdrawalTransaction = new Transaction();
        withdrawalTransaction.setAccount(account);
        withdrawalTransaction.setTimestamp(new Date());
        withdrawalTransaction.setAmount(-amount); // Negative amount for withdrawal
        withdrawalTransaction.setDescription(description);

        transactionRepository.save(withdrawalTransaction);
    }
}
