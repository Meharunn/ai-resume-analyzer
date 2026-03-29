package com.ai.resume_analyzer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.resume_analyzer.Entity.User;
import com.ai.resume_analyzer.dto.ApiResponse;
import com.ai.resume_analyzer.repository.UserRepository;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // REGISTER API
    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {

        // check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new ApiResponse(false, "Email already exists!");
        }

        user.setPaid(false);
        userRepository.save(user);

        return new ApiResponse(true, "User Registered Successfully");
    }
    
    //LOGIN
    @PostMapping("/login")
    public ApiResponse login(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElse(null);

        if (existingUser == null) {
            return new ApiResponse(false, "User not found");
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return new ApiResponse(false, "Invalid password");
        }

        return new ApiResponse(true, "Login successful");
    }
}