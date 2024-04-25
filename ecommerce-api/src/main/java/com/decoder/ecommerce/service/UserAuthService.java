package com.decoder.ecommerce.service;

import com.decoder.ecommerce.config.JwtProvider;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.implementation.CustomUserDetailServiceImpl;
import com.decoder.ecommerce.model.Cart;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.repository.CartRepository;
import com.decoder.ecommerce.repository.UserRepository;
import com.decoder.ecommerce.request.LoginRequest;
import com.decoder.ecommerce.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;

    public UserAuthService() {}

    public AuthResponse registerUser(User user) throws UserException {

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        User isExist = userRepository.findByEmail(email);

        if(isExist != null)
            throw new UserException("Email Already Used With Another Account");

        User createdUser = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .firstName(firstName)
                .lastName(lastName)
                .build();

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Registered Successfully!");

        Cart cart = cartService.createCart(createdUser);

        return authResponse;
    }

    public  AuthResponse loginUser(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Successfully!");

        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid Username...");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password...");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
