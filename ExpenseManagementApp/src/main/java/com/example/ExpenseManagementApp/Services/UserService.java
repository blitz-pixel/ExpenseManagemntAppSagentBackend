package com.example.ExpenseManagementApp.Services;

import com.example.ExpenseManagementApp.Model.User;
import com.example.ExpenseManagementApp.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // For testing
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

}

