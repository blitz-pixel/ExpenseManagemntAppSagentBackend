package com.example.ExpenseManagementApp.Services;

import com.example.ExpenseManagementApp.DTO.LoginDTO;
import com.example.ExpenseManagementApp.Model.User;
import com.example.ExpenseManagementApp.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // For testing
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        return userRepository.findByEmail(email, password).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Modify this method to load the user by email (or username)
        Optional<User> userOptional = userRepository.findByEmailOnly(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Return a custom UserDetails object (you can use CustomUserDetails)
        User user = userOptional.get();
        return new userDetailsService(user);
}

}

