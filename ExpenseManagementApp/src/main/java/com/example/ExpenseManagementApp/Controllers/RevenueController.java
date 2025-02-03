package com.example.ExpenseManagementApp.Controllers;

import com.example.ExpenseManagementApp.DTO.RevenueRequest;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import sun.util.logging.PlatformLogger;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/revenue")
public class RevenueController {
    Logger logger = Logger.getLogger(getClass().getName());


    public TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRevenues(@RequestParam Long account_id) {
        try {
            return ResponseEntity.ok(transactionService.getRevenueTransactions(account_id));
        } catch (Exception e) {
            logger.info("Error Revenue : " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRevenue(@RequestBody RevenueRequest request) {

        try {
            if (request == null) {
                logger.warning("Request body is null");
                return ResponseEntity.badRequest().body("Request body is missing");
            }

            logger.info("Received Request: Account ID = " + request.getAccountId() +
                    ", Description = " + request.getDescription() +
                    ", Amount = " + request.getAmount() +
                    ", Category ID = " + request.getCategoryId()
            );

            if (request.getAccountId() == null || request.getAmount() == null) {
                return ResponseEntity.badRequest().body("Missing required fields in the request body");
            }

            Transaction transaction = transactionService.addRevenue(
                    request.getAccountId(),
                    request.getDescription(),
                    request.getAmount(),
                    request.getCategoryId()
            );

            return ResponseEntity.ok("Transaction added successfully");
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


}
