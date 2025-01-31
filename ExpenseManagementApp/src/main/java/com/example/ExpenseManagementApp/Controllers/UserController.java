package com.example.ExpenseManagementApp.Controllers;

import com.example.ExpenseManagementApp.Configuration.JwtUtil;
import com.example.ExpenseManagementApp.DTO.LoginDTO;
import com.example.ExpenseManagementApp.DTO.RegisterDTO;
import com.example.ExpenseManagementApp.Model.User;
import com.example.ExpenseManagementApp.Services.UserService;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;



    @PostMapping("/Login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Logger logger = Logger.getLogger(UserController.class.getName()); // Create a logger instance

        try {
            String token = userService.authenticateUser(loginDTO);
            logger.info(token);
            return ResponseEntity.ok().header("Authorization","Bearer " + token).body("Login successful");
//
        } catch (Exception e) {
            // Log the exception
            logger.severe("Login failed due to an error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/Registration")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        try {
            userService.addUserPersonal(registerDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}