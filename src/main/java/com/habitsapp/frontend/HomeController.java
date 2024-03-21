package com.habitsapp.frontend;

import jakarta.persistence.Column;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("message","Welcome in HabitsApp");
        return "home";
    }
}
