package com.example.ExpenseManagementApp.Controllers;

import com.example.ExpenseManagementApp.Model.Account;
import com.example.ExpenseManagementApp.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/profile")
    public List<Account> getUserAccounts(){
//        String username = authentication.getName();
        return accountService.getAccountsByUsername("admin");
    }
}