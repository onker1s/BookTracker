package booktracker.service;

import booktracker.data.UserRepository;
import booktracker.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public User findByUsername(String username) {
        System.out.println(username);
        return userRepo.findByUsername(username).orElse(null);
    }
}
