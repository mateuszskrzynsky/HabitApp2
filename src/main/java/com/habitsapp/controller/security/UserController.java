package com.habitsapp.controller.security;

import com.habitsapp.model.security.User;
import com.habitsapp.service.security.UserService;
import com.habitsapp.utils.exception.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/registration")
    public String showRegistration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user) throws UserAlreadyExistException {
        userService.registerUser(user);
        return "redirect:/registration?success";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return ResponseEntity.ok("User loggin succesfully");
    }
}
