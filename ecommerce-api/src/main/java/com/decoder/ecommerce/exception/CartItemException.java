package com.decoder.ecommerce.exception;

import com.decoder.ecommerce.service.CartItemService;

public class CartItemException extends Exception {
    public CartItemException(String message) {
        super(message);
    }
}
