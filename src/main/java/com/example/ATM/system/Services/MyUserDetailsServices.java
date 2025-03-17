package com.example.ATM.system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ATM.system.Models.Account;
import com.example.ATM.system.Repository.AccountRepository;
@Service
public class MyUserDetailsServices implements UserDetailsService {

    @Autowired 
    AccountRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByAccountNumber(username);
        if(account==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User: "+username);
        return new MyUserDetails(account);
    }
}
