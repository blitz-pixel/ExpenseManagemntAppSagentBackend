package com.example.ExpenseManagementApp.Services;

import com.example.ExpenseManagementApp.DTO.TransactionDTO;
import com.example.ExpenseManagementApp.Model.Account;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Repositories.AccountRepository;
import com.example.ExpenseManagementApp.Repositories.CategoryRepository;
import com.example.ExpenseManagementApp.Repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    Logger logger = Logger.getLogger(TransactionService.class.getName());

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public List<TransactionDTO> getTransactions(Long accountId, Category.CatType type) {
        List<Transaction> transactions = transactionRepository.findAllByTypeAndAccountId(type, accountId);
        logger.info(transactions.toString());
        return transactions.stream().map(TransactionDTO::new).toList();
    }

    @Transactional
    public Transaction addTransaction(TransactionDTO transactionDTO, Category.CatType type) {
        validateTransactionDTO(transactionDTO);

        Account account = getAccount(transactionDTO.getAccount_id());
        Category category = getCategory(transactionDTO.getParentCategoryName());
        Transaction transaction = createTransaction(transactionDTO, account, category, type);

        return transactionRepository.save(transaction);
    }

    private void validateTransactionDTO(TransactionDTO transactionDTO) {
        if (transactionDTO.getAccount_id() == null) {
            throw new RuntimeException("Account id is required");
        }
    }

    private Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(
                () -> new IllegalArgumentException("Account not found"));
    }

    private Category getCategory(String parentCategoryName) {
        return categoryRepository.findByName(parentCategoryName).orElseThrow(
                () -> new IllegalArgumentException("Category not found"));
    }

    private Transaction createTransaction(TransactionDTO transactionDTO, Account account, Category category, Category.CatType type) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDate(Instant.now());
        transaction.setCategory(getCategoryForTransaction(transactionDTO, category));
        if (transactionDTO.getDescription() != null && !transactionDTO.getDescription().isEmpty()) {
            transaction.setDescription(transactionDTO.getDescription());
        }
        return transaction;
    }

    private Category getCategoryForTransaction(TransactionDTO transactionDTO, Category parentCategory) {
        if (transactionDTO.getSubCategoryName() != null && !transactionDTO.getSubCategoryName().isEmpty()) {
            return categoryRepository.findByParent(transactionDTO.getSubCategoryName(), parentCategory.getId())
                    .orElseThrow(() -> new IllegalArgumentException("SubCategory not found"));
        } else {
            return parentCategory;
        }
    }
}
