package com.example.ExpenseManagementApp.Services;


import com.example.ExpenseManagementApp.DTO.ExpenseRequestDTO;
import com.example.ExpenseManagementApp.DTO.ExpenseResponseDTO;
import com.example.ExpenseManagementApp.Model.Account;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Repositories.AccountRepository;
import com.example.ExpenseManagementApp.Repositories.CategoryRepository;
import com.example.ExpenseManagementApp.Repositories.TransactionRepository;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Service
public class TransactionService {
    private final CategoryRepository categoryRepository;
    Logger logger = Logger.getLogger(TransactionService.class.getName());
    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ExpenseResponseDTO> getExpenseTransactions(Long accountId) {
        List<Transaction> ExpenseList = transactionRepository.findAllByTypeAndAccountId(Category.CatType.expense, accountId);
//        ExpenseList.forEach(transaction -> Hibernate.initialize(transaction.getCategory()));
//        ExpenseList.forEach(transaction -> Hibernate.initialize(transaction.getAccount()));
        List<ExpenseResponseDTO> ExpenseResponseDTOs = ExpenseList.stream()
                .map(ExpenseResponseDTO::new)
                .toList();
        logger.info(ExpenseList.toString());
        return ExpenseResponseDTOs;
    }


    // For testing return type is Transaction
    @Transactional
    public Transaction addExpenseTransaction(ExpenseRequestDTO expenseDTO) {
        if (expenseDTO.getAccount_id() == null) throw new RuntimeException("Account id is required");
        Account account = accountRepository.findById(expenseDTO.getAccount_id()).orElseThrow(
                () -> new IllegalArgumentException("Account not found"));
        Category category = categoryRepository.findByName(expenseDTO.getCategoryName()).orElseThrow(
                () -> new IllegalArgumentException("Category not found"));
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(Category.CatType.expense);
        transaction.setCategory(category);
        transaction.setAmount(expenseDTO.getAmount());
        transaction.setDate(Instant.now());
        if ( expenseDTO.getSubCategoryName() != null && !(expenseDTO.getSubCategoryName().isEmpty()) ) {
            Category subCategory = categoryRepository.findByParent(expenseDTO.getSubCategoryName(),category.getId()).orElseThrow(
                    () -> new IllegalArgumentException("SubCategory not found"));
            transaction.setCategory(subCategory);
        }
        if (expenseDTO.getDescription() != null && !(expenseDTO.getDescription().isEmpty())) transaction.setDescription(expenseDTO.getDescription());
        return  transactionRepository.save(transaction);
    }
}
