package com.example.ExpenseManagementApp.Tests;

import com.example.ExpenseManagementApp.DTO.ExpenseRequestDTO;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Repositories.CategoryRepository;
import com.example.ExpenseManagementApp.Repositories.TransactionRepository;
import com.example.ExpenseManagementApp.Services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionTests {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;



    @Test
    public void testThatCreatesExpense(){
        ExpenseRequestDTO expenseRequestDTO = new ExpenseRequestDTO();
        expenseRequestDTO.setAccount_id(22L);
        expenseRequestDTO.setAmount(BigDecimal.valueOf(1000L));
        expenseRequestDTO.setCategoryName("Food");
        expenseRequestDTO.setDescription("Lunch");
        Transaction transaction = transactionService.addExpenseTransaction(expenseRequestDTO);

        assertNotNull(transaction, "Category should be saved");
    }

    @Test
    public void testThatCreatesSubCategory(){
        ExpenseRequestDTO expenseRequestDTO = new ExpenseRequestDTO();
        expenseRequestDTO.setAccount_id(22L);
        expenseRequestDTO.setCategoryName("Food");
        expenseRequestDTO.setSubCategoryName("Burger");
        expenseRequestDTO.setAmount(BigDecimal.valueOf(1000L));
        expenseRequestDTO.setDate(Instant.now());
        transactionService.addExpenseTransaction(expenseRequestDTO);
    }
}
