package com.example.ATM.system.Services;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ATM.system.Models.Account;


public class MyUserDetails implements UserDetails{

    Account account;
    public MyUserDetails(Account account){
        this.account=account;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); 
    }

    @Override
    public String getPassword() {
        System.out.println("pin number: "+account.getPin());
        return account.getPin();
    }

    @Override
    public String getUsername() {
        System.out.println("account number: "+account.getAccountNumber());
        return account.getAccountNumber();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
