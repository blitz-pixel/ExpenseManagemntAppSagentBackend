package com.example.ExpenseManagementApp.Services;

import com.example.ExpenseManagementApp.DTO.ExpenseDTO;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Repositories.AccountRepository; // import the AccountRepository
import com.example.ExpenseManagementApp.Repositories.CategoryRepository; // import the CategoryRepository
import com.example.ExpenseManagementApp.Repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionService {

    Logger logger = Logger.getLogger(TransactionService.class.getName());

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;  // Inject AccountRepository
    private final CategoryRepository categoryRepository;  // Inject CategoryRepository

    // Constructor injection of repositories
    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;  // Assigning to the instance variable
        this.categoryRepository = categoryRepository;  // Assigning to the instance variable
    }

    public List<ExpenseDTO> getExpenseTransactions(Long accountId) {
        List<Transaction> ExpenseList = transactionRepository.findAllByTypeAndAccountId(Category.CatType.expense, accountId);
        ExpenseList.forEach(transaction -> Hibernate.initialize(transaction.getCategory()));
        ExpenseList.forEach(transaction -> Hibernate.initialize(transaction.getAccount()));
        List<ExpenseDTO> ExpenseDTOs = ExpenseList.stream()
                .map(ExpenseDTO::new)
                .toList();
        logger.info(ExpenseList.toString());
        return ExpenseDTOs;
    }

    // Method to add revenue
    public Transaction addRevenue(Long accountId, String description, Double amount, Long categoryId) {
        Transaction transaction = new Transaction();
        transaction.setAccount(accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found")));
        transaction.setDescription(description);
        transaction.setAmount(BigDecimal.valueOf(amount));
        transaction.setType(Category.CatType.revenue);
        transaction.setCategory(categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found")));
        transaction.setDate(Instant.now());

        return transactionRepository.save(transaction);
    }
}
