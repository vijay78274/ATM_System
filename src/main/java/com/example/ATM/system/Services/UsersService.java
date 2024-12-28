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
}
