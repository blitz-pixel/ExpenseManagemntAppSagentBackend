package com.example.ExpenseManagementApp.Services;

import com.example.ExpenseManagementApp.Configuration.JwtUtil;
import com.example.ExpenseManagementApp.DTO.LoginDTO;
import com.example.ExpenseManagementApp.DTO.RegisterDTO;
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
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // For testing
//    @Transactional
//    public User addUser(User user) {
//        return userRepository.save(user);
//    }

    public void addUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUserName(registerDTO.getUserName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        userRepository.save(user);
    }

    public String authenticateUser(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + loginDTO.getEmail());
        }

        User user = userOptional.get();

        // Validate password
        if (!(user.getPassword().equals(loginDTO.getPassword()))) {
            throw new UsernameNotFoundException("Invalid password");
        }

        // Generate JWT Token
        return jwtUtil.generateToken(user.getEmail());
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Modify this method to load the user by email (or username)
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Return a custom UserDetails object (you can use CustomUserDetails)
        User user = userOptional.get();
        return new CustomUserDetails(user);
}

}

