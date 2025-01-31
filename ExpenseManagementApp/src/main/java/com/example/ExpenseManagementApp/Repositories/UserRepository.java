package com.example.ExpenseManagementApp.Repositories;

import com.example.ExpenseManagementApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
//    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
//    Optional<User> findByEmail(String email,String password);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmailOnly(String email);


    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}
