package com.example.ExpenseManagementApp.Tests;

import com.example.ExpenseManagementApp.DTO.CategoryDTO;
import com.example.ExpenseManagementApp.DTO.ExpenseRequestDTO;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Model.Transaction;
import com.example.ExpenseManagementApp.Repositories.CategoryRepository;
import com.example.ExpenseManagementApp.Services.CategoryService;
import com.example.ExpenseManagementApp.Services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CategoryService categoryService;


    @Test
    public  void  testThatReturnsCategoryByName(){
        Category c = categoryRepository.findByName("Food").orElse(null);

        assertNotNull(c, "Category should not be null when it exists.");
    }

    @Test
    public void testThatCreatesCategory(){
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO(null, 42L, "", "Grocery", Category.CatType.expense);
        Category savedCategory = categoryService.createCategory(categoryDTO);



        assertNotNull(savedCategory, "Category should be saved");
    }

    @Test
    public void testThtFindsSubCategory(){
        Category c = categoryRepository.findByParent("Burger",5L).orElse(null);

        assertNotNull(c, "Sub Category should not be null when it exists.");
    }

    @Test
    public void testThtFindsAllCategories(){
        List<Category> Categories = categoryRepository.findCategoriesById(42L);

        assertNotNull(Categories, "Category should not be null when it exists.");
    }


}
