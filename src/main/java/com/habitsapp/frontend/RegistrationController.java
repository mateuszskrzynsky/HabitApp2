package com.habitsapp.frontend;

import com.habitsapp.model.security.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    /**
     * Handles the GET request for the user registration page.
     * Maps to the '/registration' URL and is responsible for preparing
     * a new User object to be filled out in a form on the registration page.
     * This method adds the newly created User object to the model under the attribute
     * name "user", which makes it accessible within the view.
     *
     * @param model The Model object used to add attributes to the model.
     *              This is automatically provided by Spring MVC and acts as a container
     *              for model attributes. In this case, it's used to pass the User
     *              object to the view.
     * @return This method returns "registration.html",
     *         indicating that the request will be forwarded to the "registration.html" view.
     *         This view will display the user registration form.
     */
    @GetMapping("/registration")
    public String showRegistration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
}
