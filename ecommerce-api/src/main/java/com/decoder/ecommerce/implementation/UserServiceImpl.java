package com.decoder.ecommerce.implementation;

import com.decoder.ecommerce.config.JwtProvider;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.repository.UserRepository;
import com.decoder.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UserException("User not found with id "+userId));
    }

    @Override
    public User findUserByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UserException("User not found with email: "+email);
        }
        return user;
    }

    public Long findUserIdByEmail(String email) {
        return userRepository.findByEmail(email).getId();
    }
}
