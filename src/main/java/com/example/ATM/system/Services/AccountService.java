package com.example.ATM.system.Services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ATM.system.Controller.AccountController;
import com.example.ATM.system.Models.Account;
import com.example.ATM.system.Models.Users;
import com.example.ATM.system.Repository.AccountRepository;
import com.example.ATM.system.Repository.UsersRepository;

@Service
public class AccountService {
    @Autowired 
    private AccountRepository repository;
    @Autowired
    UsersRepository repository2;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Account createAccount(String pin, String name, String email, Double balance) {
        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        // account.setBalance(balance); 
        String accountNumber = generateUniqueAccountNumber();
        account.setAccountNumber(accountNumber);
        account.setPin(passwordEncoder.encode(pin));
        Users users = new Users();
        users.setAccountNumber(accountNumber);
        users.setBalance(balance);
        repository2.save(users);
        return repository.save(account);
    }

    private String generateUniqueAccountNumber() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
