package com.example.ExpenseManagementApp.Services;


import com.example.ExpenseManagementApp.DTO.ExpenseDTO;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionService {
    Logger logger = Logger.getLogger(TransactionService.class.getName());

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<ExpenseDTO> getExpenseTransactions(Long accountId) {
        List<Transaction> ExpenseList = transactionRepository.findAllByTypeAndAccountId(Category.CatType.expense, accountId);
//        ExpenseList.forEach(transaction -> Hibernate.initialize(transaction.getCategory()));
//        ExpenseList.forEach(transaction -> Hibernate.initialize(transaction.getAccount()));
        List<ExpenseDTO> ExpenseDTOs = ExpenseList.stream()
                .map(ExpenseDTO::new)
                .toList();
        logger.info(ExpenseList.toString());
        return ExpenseDTOs;
    }
}
