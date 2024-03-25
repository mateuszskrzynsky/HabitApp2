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

    public User registerUser(User user) throws UserAlreadyExistException {
        if (emailExist(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}