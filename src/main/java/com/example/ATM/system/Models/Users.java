package com.example.ATM.system.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "accountNumber",unique = true, nullable = false)
    private String accountNumber;
    // @Column(name = "name")
    // private String name;
    // @Column(name = "email")
    // private String email;
    @Column(name = "balance")
    private Double balance;
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    // public String getName() {
    //     return name;
    // }
    // public void setName(String name) {
    //     this.name = name;
    // }
    // public String getEmail() {
    //     return email;
    // }
    // public void setEmail(String email) {
    //     this.email = email;
    // }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}
