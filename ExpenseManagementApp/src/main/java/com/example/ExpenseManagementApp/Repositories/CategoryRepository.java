package com.example.ExpenseManagementApp.Repositories;

import com.example.ExpenseManagementApp.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT * FROM category WHERE scope_id = :scopeId", nativeQuery = true)
    List<Category> findCategoriesByScopeId(@Param("scopeId") Long scopeId);
}
