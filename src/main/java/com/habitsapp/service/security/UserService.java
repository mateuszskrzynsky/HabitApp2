package com.habitsapp.service.security;

import com.habitsapp.model.security.User;
import com.habitsapp.repository.security.UserRepository;
import com.habitsapp.utils.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user by saving their details to the database.
     * Before saving, it checks if the user's email already exists in the database to prevent duplicates.
     * If the email is unique, it encodes the user's password for secure storage and then saves the user.
     *
     * @param user The User object containing the new user's registration details.
     * @return The User object after it has been saved to the database.
     * @throws UserAlreadyExistException if a user with the given email already exists.
     */
    public User registerUser(User user) throws UserAlreadyExistException {
        if (emailExist(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    /**
     * Checks if an email already exists in the database.
     *
     * @param email The email address to check against existing records.
     * @return true if the email is found in the database, false otherwise.
     */
    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}