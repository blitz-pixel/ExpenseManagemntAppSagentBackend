package com.example.ExpenseManagementApp.Services;


import com.example.ExpenseManagementApp.Model.Account;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Repositories.AccountRepository;
import com.example.ExpenseManagementApp.Repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, AccountRepository accountRepository) {
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

//    public void createCategory(Category category, Long accountId) {
//        category.setAccount(accountId);
//        categoryRepository.save(category);
//    }

//    public Long isSharedAccount(Long accountId) throws SQLException {
//        Account account = accountRepository.findById(accountId).orElse(null);
//        if (account == null) {
//            throw new SQLException("Account not found");
//        }
//
//        if ("shared".equals(account.getType())){
//            return account.getAccount_id();
//        } else {
//            return  account.getUser_Foriegn_id().getUser_id();
//        }
//    }

   public List<Category> getCategories(Long scopeId){
        return categoryRepository.findCategoriesByScopeId(scopeId);
   }


    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        logger.info("getAllCategories: {}", categories);
        return categories;
    }
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

//    public Category getCategoryById(Long id) {
//        return categoryRepository.findById(id).get();
//    }

    public List<Category> getCategoriesByType(Category.CatType type) {
        return categoryRepository.findByType(type);
    }
}
