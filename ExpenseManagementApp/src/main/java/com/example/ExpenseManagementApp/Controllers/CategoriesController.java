package com.example.ExpenseManagementApp.Controllers;

import com.example.ExpenseManagementApp.DTO.CategoryDTO;
import com.example.ExpenseManagementApp.Model.Category;
import com.example.ExpenseManagementApp.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    Logger logger = Logger.getLogger(CategoriesController.class.getName());
    @PostMapping("/add")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
//            Long account_scope_id = categoryService.isSharedAccount(accountId);
            categoryService.createCategory(categoryDTO);
            return ResponseEntity.ok("Category created successfully");
        } catch (Exception e) {
            // Log the exception and return an error response
          logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/{accountId}/categories")
//    public ResponseEntity<List<Category>> getCategories(@PathVariable Long accountId) {
//        try {
//            Long account_scope_id = categoryService.isSharedAccount(accountId);
//            List<Category> categories = categoryService.getCategories(account_scope_id);
//            return new ResponseEntity<>(categories, HttpStatus.OK);
//        } catch (SQLException e) {
//            // Log the exception and return an error response
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
