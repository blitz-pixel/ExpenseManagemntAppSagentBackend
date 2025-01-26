package com.example.ExpenseManagementApp.Tests;

import com.example.ExpenseManagementApp.Model.User;
import com.example.ExpenseManagementApp.Repositories.UserRepository;
import com.example.ExpenseManagementApp.Services.UserService;
import com.example.ExpenseManagementApp.TestUtils.UserTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class RegisterTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // Initialize UserTest manually if it's not autowired
    private final UserTest userTest = new UserTest();

    @Test
    public void testRegister() {
        // Get a test user from userTest
        User user = userTest.testUserDetails();

        // Mock the behavior of the repository: Simulating saving the user
        when(userRepository.save(user)).thenReturn(user);

        // Call the service method to add the user
        User savedUser = userService.addUser(user);


        // Verify that the user is saved correctly
        assertNotNull(savedUser, "User should be saved");
        assertEquals("John Doe", savedUser.getUserName(), "Usernames should match");
        assertEquals("John123@gmail.com", savedUser.getEmail(), "Emails should match");
        assertEquals("123456", savedUser.getPassword(), "Passwords should match");
        assertNotNull(savedUser.getCreatedAt(), "Created at should not be null");
        assertEquals(1L,savedUser.getUser_id(),"Id no match");

        // Verify that the save method was called once
        verify(userRepository, times(1)).save(user);
    }
}
