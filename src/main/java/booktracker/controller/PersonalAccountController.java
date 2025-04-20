package booktracker.controller;

import booktracker.domain.Status;
import booktracker.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class PersonalAccountController {
    @GetMapping
    public String userAccount() {
        return "personalAccount";
    }
}
