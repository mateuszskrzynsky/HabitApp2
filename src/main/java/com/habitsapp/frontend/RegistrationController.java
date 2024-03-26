package com.habitsapp.frontend;

import com.habitsapp.model.security.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String showRegistration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
}
