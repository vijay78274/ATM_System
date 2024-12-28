package com.example.ATM.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ATM.system.Models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,String>{
    Users findByAccountNumber(String accountNumber);
}
