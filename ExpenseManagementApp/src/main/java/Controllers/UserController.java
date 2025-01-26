package Controllers;

import DTO.LoginDTO;
import DTO.RegisterDTO;
import Model.User;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/Login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.getUserByEmail(loginDTO.getEmail());
            if (user == null) {
                return ResponseEntity.badRequest().build();
            }
            if (user.getPassword().equals(loginDTO.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/Registration")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        try {
            User user = new User();
            user.setEmail(registerDTO.getEmail());
            user.setPassword(registerDTO.getPassword());
            user.setUserName(registerDTO.getUserName());
            userService.addUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}