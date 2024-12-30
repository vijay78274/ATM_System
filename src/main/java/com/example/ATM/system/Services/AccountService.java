package com.example.ATM.system.Services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ATM.system.Models.Account;
import com.example.ATM.system.Repository.AccountRepository;

@Service
public class AccountService {
    @Autowired 
    AccountRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Account createAccount(String pin, String name, String email, Double balance) {
        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setBalance(0.0); 
        String accountNumber = generateUniqueAccountNumber();
        account.setAccountNumber(accountNumber);

        account.setPin(passwordEncoder.encode(pin));
        return repository.save(account);
    }

    private String generateUniqueAccountNumber() {
        return String.valueOf(UUID.randomUUID()).substring(0, 10);
    }
}
