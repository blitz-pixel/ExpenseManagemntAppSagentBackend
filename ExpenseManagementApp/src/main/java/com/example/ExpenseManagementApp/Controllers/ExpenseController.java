package com.example.ExpenseManagementApp.Controllers;

import com.example.ExpenseManagementApp.DTO.RevenueRequest;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final TransactionService expenseService;
    Logger logger = Logger.getLogger(ExpenseController.class.getName());

    public ExpenseController(TransactionService expenseService) {
        this.expenseService = expenseService;
    }
}
