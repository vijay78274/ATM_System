package com.example.ATM.system.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ATM.system.Models.Account;
import com.example.ATM.system.Services.UsersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/atm")
public class UsersController {
    @Autowired 
    private UsersService usersService;
    
    @GetMapping("/main/balance")
    public String CheckBalance(@RequestParam String AccountNumber, Model model) {
        Double balance = usersService.getBalance(AccountNumber);
        model.addAttribute("accountNumber", AccountNumber);
        model.addAttribute("balance", balance);
        return "check-balance";
    }
    @GetMapping("/main/withdraw")
    public String withdrawString() {
        return "Withdraw";
    }
    @PostMapping("/main/withdraw")
    public  ResponseEntity<String> withdrawMoney(@RequestBody Map<String, Object> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("account number: "+username);
        try {
            double amount = Double.parseDouble(request.get("amount").toString());
            usersService.withdraw(username, amount);
            return ResponseEntity.ok("Withdrawal of " + amount + " was successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid amount format.");
        }
    }
}
