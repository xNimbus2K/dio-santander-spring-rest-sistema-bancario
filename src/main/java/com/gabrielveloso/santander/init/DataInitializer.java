package com.gabrielveloso.santander.init;

import com.gabrielveloso.santander.model.Account;
import com.gabrielveloso.santander.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        createTestAccounts();
    }

    private void createTestAccounts() {
        // Crie contas de teste
        Account account1 = new Account();
        account1.setAccountNumber("123456");
        account1.setAccountHolder("Alice");
        account1.setBalance(1000.0);

        Account account2 = new Account();
        account2.setAccountNumber("789012");
        account2.setAccountHolder("Bob");
        account2.setBalance(2000.0);

        // Salve as contas no banco de dados
        accountRepository.save(account1);
        accountRepository.save(account2);
    }
}
