package com.decoder.ecommerce.controller;

import com.decoder.ecommerce.config.JwtVaildator;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.repository.UserRepository;
import com.decoder.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtVaildator jwtVaildator;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Principal principal) {
        System.out.println(principal.getName());
        return ResponseEntity.ok(userRepository.findByEmail(principal.getName()));
    }
}
