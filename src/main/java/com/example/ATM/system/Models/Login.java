package com.example.ATM.system.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Login")
public class Login{
    @Id
    @Column(name="accountNumber")
    private String accountNumber;
    @Column(name="password")
    private String password;
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getPassword() {
        return password;
    }
    public Login(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }
}