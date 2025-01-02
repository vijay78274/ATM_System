package com.example.ATM.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ATM.system.Models.Account;
import com.example.ATM.system.Models.Users;
import com.example.ATM.system.Services.AccountService;
import com.example.ATM.system.Services.UsersService;

@SpringBootTest
class AtmSystemApplicationTests {

	@Autowired 
	AccountService service;
	UsersService service2;
	@Test
	void contextLoads() {
	}

	@Test
	void saveAccount(){
		Account account = service.createAccount("2001", "Vijay Singh", "vijaynayal@gmail.com", 10000.00);
		System.out.println(account);
	}
}
