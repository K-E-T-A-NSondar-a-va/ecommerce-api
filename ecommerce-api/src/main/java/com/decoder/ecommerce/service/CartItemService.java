package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.CartItemException;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.Cart;
import com.decoder.ecommerce.model.CartItem;
import com.decoder.ecommerce.model.Product;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
