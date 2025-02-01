//package com.example.ExpenseManagementApp.Controllers;
//
//import com.example.ExpenseManagementApp.Model.Category;
//import com.example.ExpenseManagementApp.Services.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.SQLException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1")
//public class CategoriesController {
//    private final CategoryService categoryService;
//
//    @Autowired
//    public CategoriesController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @PostMapping("/{accountId}/categories")
//    public ResponseEntity<String> createCategory(@PathVariable Long accountId, @RequestBody Category category) {
//        try {
//            Long account_scope_id = categoryService.isSharedAccount(accountId);
//            categoryService.createCategory(category, account_scope_id);
//            return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
//        } catch (SQLException e) {
//            // Log the exception and return an error response
//            return new ResponseEntity<>("Error creating category: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
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
//}
