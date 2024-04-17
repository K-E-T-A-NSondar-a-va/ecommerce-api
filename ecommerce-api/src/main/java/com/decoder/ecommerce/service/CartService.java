package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Cart;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.request.AddItemRequest;

public interface CartService {
    Cart createCart(User user);
    String addCartItem(Long userId, AddItemRequest request) throws ProductException;
    Cart findUserCart(Long userId);
}
