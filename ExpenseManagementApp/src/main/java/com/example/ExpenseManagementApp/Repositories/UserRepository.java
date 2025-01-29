package com.example.ExpenseManagementApp.Repositories;

import com.example.ExpenseManagementApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    Optional<User> findByEmail(String email,String password);
}
