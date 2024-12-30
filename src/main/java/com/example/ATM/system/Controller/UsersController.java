package com.example.ATM.system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ATM.system.Services.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/atm")
public class UsersController {
    @Autowired 
    private UsersService usersService;
    
    @GetMapping("/balance")
    public String CheckBalance(@RequestParam String AccountNumber, Model model) {
        Double balance = usersService.getBalance(AccountNumber);
        model.addAttribute("accountNumber", AccountNumber);
        model.addAttribute("balance", balance);
        return "check-balance";
    }
    
}
