package com.example.ATM.system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ATM.system.Models.Account;
import com.example.ATM.system.Services.AccountService;

@Controller
@RequestMapping("/atm")
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam String pin, @RequestParam String name, @RequestParam String email, @RequestParam Double balance){
        Account account = service.createAccount(pin, name, email, balance);
        return ResponseEntity.ok(account);
    }
}
