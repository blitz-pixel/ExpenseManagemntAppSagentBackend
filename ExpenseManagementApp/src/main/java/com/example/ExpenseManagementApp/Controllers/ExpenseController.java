package com.example.ExpenseManagementApp.Controllers;

import com.example.ExpenseManagementApp.DTO.ExpenseDTO;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Services.TransactionService;
import com.example.ExpenseManagementApp.Services.UserService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    Logger logger = Logger.getLogger(ExpenseController.class.getName()); // Create a logger instance
    private final TransactionService expenseService;

    public ExpenseController(TransactionService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getExpensesByAccount(@RequestParam Long accountId){
        try {
//            List<Transaction> TransactionList = expenseService.getExpenseTransactions(accountId);
//            logger.info(TransactionList.toString());
//            for (Transaction transaction : TransactionList) {
//                logger.info(transaction.toString());
//            }
            return ResponseEntity.ok(expenseService.getExpenseTransactions(accountId));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

}