package booktracker.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import booktracker.data.UserRepository;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm form, Model model) {
        if(userRepo.existsByUsername(form.getUsername())) {
            model.addAttribute("error", "Это имя пользователя занято");
            return "registration";
        }
        else if (userRepo.existsByEmail(form.getEmail())) {
            model.addAttribute("error", "Этот адрес электронной почты уже используется");
            return "registration";
        }
        else {
            userRepo.save(form.toUser(passwordEncoder));
            return "redirect:/login";
        }

    }
}