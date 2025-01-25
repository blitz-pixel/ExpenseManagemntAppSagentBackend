package Controllers;

import Model.User;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/Login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/Registration")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}