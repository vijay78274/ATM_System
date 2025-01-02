package com.example.ATM.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ATM.system.Models.Account;

public interface AccountRepository extends JpaRepository<Account,String> {
}
