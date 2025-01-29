package com.example.ExpenseManagementApp.Controllers;

import com.example.ExpenseManagementApp.Configuration.JwtUtil;
import com.example.ExpenseManagementApp.DTO.LoginDTO;
import com.example.ExpenseManagementApp.DTO.RegisterDTO;
import com.example.ExpenseManagementApp.Model.User;
import com.example.ExpenseManagementApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/Login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Logger logger = Logger.getLogger(UserController.class.getName()); // Create a logger instance

        try {
            // Look up the user by email
            User user = userService.getUserByEmail(loginDTO);
            String user_name = user.getUserName();

            if (user == null) {
                // Log and return error if user is not found
                logger.warning("Login failed: User with email " + loginDTO.getEmail() + " not found.");
                return ResponseEntity.badRequest().body("User not found");
            }

            // Check if password matches
            if (user.getPassword().equals(loginDTO.getPassword())) {
                // Log successful login
                logger.info("Login successful: User with email " + loginDTO.getEmail() + " logged in.");
                logger.info(jwtUtil.generateToken(user_name));

                return ResponseEntity.ok("Login successful");
            } else {
                // Log failed login attempt due to incorrect password
                logger.warning("Login failed: Incorrect password for user with email " + loginDTO.getEmail());
                return ResponseEntity.badRequest().body("Password not equal");
            }
        } catch (Exception e) {
            // Log the exception
            logger.severe("Login failed due to an error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error");
        }
    }


    @PostMapping("/Registration")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        try {
            User user = new User();
            user.setUserName(registerDTO.getUserName());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(registerDTO.getPassword());
            userService.addUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}