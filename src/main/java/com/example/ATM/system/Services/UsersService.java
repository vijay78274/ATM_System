package com.example.ATM.system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ATM.system.Models.Users;
import com.example.ATM.system.Repository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Double getBalance(String AccountNumber){
        Users users = usersRepository.findByAccountNumber(AccountNumber);
        return users.getBalance(); 
    }
    public void withdraw(String accountNumber, Double amount){
        Users users = usersRepository.findByAccountNumber(accountNumber);
        if(users==null){
            System.out.println("Account not found");
        }
        Double oldBalance = users.getBalance();
        users.setBalance(oldBalance-amount);
        usersRepository.save(users);
    } 
    public void transfer(String fromAccount, String toAccount, Double amount){
        Users fromUser = usersRepository.findByAccountNumber(toAccount);
        Users toUser = usersRepository.findByAccountNumber(toAccount);
        if(fromUser==null||toUser==null){
            System.out.println("Account not found");
        }
        Double fromBalance = fromUser.getBalance();
        Double toBalance = toUser.getBalance();
        fromUser.setBalance(fromBalance-amount);
        toUser.setBalance(toBalance+amount);
        usersRepository.save(fromUser);
    }   
}
