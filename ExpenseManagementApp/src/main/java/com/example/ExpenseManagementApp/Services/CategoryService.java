package com.example.ExpenseManagementApp.Services;


import com.example.ExpenseManagementApp.DTO.CategoryDTO;
import com.example.ExpenseManagementApp.DTO.TransactionDTO;
import com.example.ExpenseManagementApp.Model.Account;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.User;
import com.example.ExpenseManagementApp.Repositories.AccountRepository;
import com.example.ExpenseManagementApp.Repositories.CategoryRepository;
import com.example.ExpenseManagementApp.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    Logger logger = Logger.getLogger(CategoryService.class.getName());
    @Autowired
    public CategoryService(CategoryRepository categoryRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Category createCategory(CategoryDTO categoryDTO) {
        Account account = null;
        User user = null;
        if (categoryDTO.getAccountId() != null) {
            account = accountRepository.findById(categoryDTO.getAccountId()).orElse(null);
        }
        if (categoryDTO.getUserId() != null) {
            user = userRepository.findById(categoryDTO.getUserId()).orElse(null);
        }

        if (account == null && user == null) {
            throw new IllegalArgumentException("Account or User does not exist");
        }

        Category parentCategory = categoryRepository.findByName(categoryDTO.getParentCategoryName()).orElse(null);
        Category subCategory = categoryRepository.findByNameAndParent(categoryDTO.getSubCategoryName(),parentCategory).orElse(null);

        if (categoryDTO.getSubCategoryName() != null && !(categoryDTO.getSubCategoryName().isEmpty()) && parentCategory == null) {
            throw new IllegalArgumentException("Parent Category does not exist");
        }



        if (subCategory != null) {
            throw new IllegalArgumentException("Sub Category already exists");
        }


        Category category = new Category();
        category.setAccount(account);
        category.setUser(user);
        category.setType(categoryDTO.getType());


        if (categoryDTO.getSubCategoryName() != null && !categoryDTO.getSubCategoryName().isEmpty()) {
            category.setName(categoryDTO.getSubCategoryName());
            category.setParent(parentCategory);
        } else {
            category.setName(categoryDTO.getParentCategoryName());
        }


        logger.info("Category created with name: " + category.getName());
        if (category.getParent() != null) {
            logger.info("Parent category: " + category.getParent().getName());
        }


        return categoryRepository.save(category);
    }


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

    public List<Category> getCategoriesByType(Category.CatType type) {
        return categoryRepository.findByType(type);
    }

    public List<CategoryDTO> getCategories(Long Id) {
        List<Category> categories = categoryRepository.findByAccountIdOrUserID(Id);
        return categories.stream()
                .map(CategoryDTO::new)
                .toList();
    }

}
