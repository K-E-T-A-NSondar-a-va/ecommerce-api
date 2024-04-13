package com.decoder.ecommerce.controller;

import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.User;

import com.decoder.ecommerce.request.LoginRequest;
import com.decoder.ecommerce.response.AuthResponse;
import com.decoder.ecommerce.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        return new ResponseEntity<AuthResponse>(userAuthService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userAuthService.loginUser(loginRequest), HttpStatus.CREATED);
    }
}
