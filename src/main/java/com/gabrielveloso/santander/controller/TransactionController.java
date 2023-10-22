package com.gabrielveloso.santander.controller;

import com.gabrielveloso.santander.dto.TransactionDTO;
import com.gabrielveloso.santander.model.Transaction;
import com.gabrielveloso.santander.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/santander")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/balance/{accountNumber}")
    public double getBalance(@PathVariable String accountNumber) {
        return transactionService.getBalance(accountNumber);
    }

    @GetMapping("/history/{accountNumber}")
    public List<Transaction> getTransactionHistory(@PathVariable String accountNumber) {
        return transactionService.getTransactionHistory(accountNumber);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransactionDTO transactionDTO) {
        transactionService.transfer(transactionDTO);

        return ResponseEntity.ok("Transferência bem-sucedida!");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionDTO transactionDTO) {
        String accountNumber = transactionDTO.getTargetAccount();
        double amount = transactionDTO.getAmount();
        String description = transactionDTO.getDescription();

        transactionService.deposit(accountNumber, amount, description);

        return ResponseEntity.ok("Depósito bem-sucedido!");
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody TransactionDTO transactionDTO) {
        String accountNumber = transactionDTO.getSourceAccount();
        double amount = transactionDTO.getAmount();
        String description = transactionDTO.getDescription();

        transactionService.withdraw(accountNumber, amount, description);
    }

}
