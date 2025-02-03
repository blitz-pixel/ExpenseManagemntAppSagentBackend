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

    @PostMapping("/revenue")
    public ResponseEntity<?> addRevenue(@RequestBody RevenueRequest request) {
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

            Transaction transaction = expenseService.addRevenue(
                    request.getAccountId(),
                    request.getDescription(),
                    request.getAmount(),
                    request.getCategoryId()
            );

            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
