package booktracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class PersonalAccountController {
    @GetMapping
    public String userAccount() {
        return "personalAccount";
    }
}
