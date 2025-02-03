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
//import java.util.logging.Logger;
//
//@RestController
//@RequestMapping("/api/v1/categories")
//public class CategoryController {
//
//    private final CategoryService categoryService;
//    Logger logger = Logger.getLogger(CategoryController.class.getName());
//
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List<Category>> getAllCategories() {
////        return ResponseEntity.ok(categoryService.getAllCategories());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
//        Category category = categoryService.getCategoryById(id);
////        logger.info(category.toString());
//        if (category != null && category.getParent() != null) {
//            System.out.println("Parent Category: " + category.getName());
//        }
//
//
//        return ResponseEntity.ok().body(category);
//    }
//
//
//    @GetMapping("/type/{type}")
//    public ResponseEntity<List<Category>> getCategoriesByType(@PathVariable Category.CatType type) {
//        return ResponseEntity.ok(categoryService.getCategoriesByType(type));
//    }
//
//
//
////    @PostMapping("/{accountId}/categories")
////    public ResponseEntity<String> createCategory(@PathVariable Long accountId, @RequestBody Category category) {
////        try {
////            Long account_scope_id = categoryService.isSharedAccount(accountId);
////            categoryService.createCategory(category, account_scope_id);
////            return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
////        } catch (SQLException e) {
////            // Log the exception and return an error response
////            return new ResponseEntity<>("Error creating category: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//
////    @GetMapping("/{accountId}/categories")
////    public ResponseEntity<List<Category>> getCategories(@PathVariable Long accountId) {
////        try {
////            Long account_scope_id = categoryService.isSharedAccount(accountId);
////            List<Category> categories = categoryService.getCategories(account_scope_id);
////            return new ResponseEntity<>(categories, HttpStatus.OK);
////        } catch (SQLException e) {
////            // Log the exception and return an error response
////            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//}