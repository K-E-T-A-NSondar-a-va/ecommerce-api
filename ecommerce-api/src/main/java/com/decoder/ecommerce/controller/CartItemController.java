package com.decoder.ecommerce.controller;

import com.decoder.ecommerce.exception.CartItemException;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.CartItem;
import com.decoder.ecommerce.service.CartItemService;
import com.decoder.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> removeTheCartItem(Principal principal, @PathVariable(name = "cartId") String cartId) throws CartItemException, UserException {
        Long userId = userService.findUserIdByEmail(principal.getName());
        cartItemService.removeCartItem(userId, Long.parseLong(cartId));
        return new ResponseEntity<>("Cart item deleted", HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public CartItem findCartItemById(@PathVariable Long cartId) throws CartItemException {
        return cartItemService.findCartItemById(cartId);
    }
}
