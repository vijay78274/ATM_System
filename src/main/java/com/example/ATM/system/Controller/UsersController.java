package com.example.ATM.system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
