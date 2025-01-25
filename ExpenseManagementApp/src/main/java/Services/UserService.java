package Services;

import Model.User;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String login(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful!";
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

    public User register(User user) {
        return userRepository.save(user);
    }
}