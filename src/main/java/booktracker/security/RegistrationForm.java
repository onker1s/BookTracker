package booktracker.security;

import booktracker.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String email;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        System.out.println(username + " " + email + " " + password);
        return new User(
                username, email, passwordEncoder.encode(password));
    }
}