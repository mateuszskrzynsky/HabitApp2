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


    /**
     * Registers a new user.
     *
     * @param user User details from registration form.
     * @return Redirects to registration page with a success message upon successful registration.
     * @throws UserAlreadyExistException If a user with the same email already exists.
     */
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user) throws UserAlreadyExistException {
        userService.registerUser(user);
        return "redirect:/registration?success";
    }

    /**
     * Processes the login request by authenticating the user.
     *
     * @param user A User containing login credentials, expected in the request body.
     * @return An HTTP 200 response with a success message if authentication succeeds.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return ResponseEntity.ok().build();
    }
}
