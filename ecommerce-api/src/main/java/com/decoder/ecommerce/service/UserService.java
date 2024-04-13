package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.User;

public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserByJwt(String jwt) throws UserException;
}
