package com.habitsapp.frontend;

import jakarta.persistence.Column;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Displays the homepage of the HabitsApp.
     *
     * @param model Holds model data for view rendering. Here, it carries a welcome message.
     * @return The name of the view to render, in this case, "home".
     */
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("message","Welcome in HabitsApp");
        return "home";
    }
}
