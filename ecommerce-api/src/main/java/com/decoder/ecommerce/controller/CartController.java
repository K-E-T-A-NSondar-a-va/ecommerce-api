package com.decoder.ecommerce.controller;

import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Cart;
import com.decoder.ecommerce.repository.UserRepository;
import com.decoder.ecommerce.request.AddItemRequest;
import com.decoder.ecommerce.response.AddItemResponse;
import com.decoder.ecommerce.service.CartService;
import com.decoder.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cart")
    public ResponseEntity<Cart> getUserCart(Principal principal) {
//        System.out.println("========================= user: "+principal.getName()+"====================================");
        Long userId = userRepository.findByEmail(principal.getName()).getId();
        return ResponseEntity.ok(cartService.findUserCart(userId));
    }

//    @Hidden // swagger annotation to hide this method in the documentation
    @PutMapping("/cart/add")
    public ResponseEntity<AddItemResponse> addItemToCart(Principal principal, @RequestBody AddItemRequest addItemRequest) throws ProductException {
        Long userId = userRepository.findByEmail(principal.getName()).getId();
        String msg = cartService.addCartItem(userId, addItemRequest);
        AddItemResponse addItemResponse = new AddItemResponse();
        if(msg.equals("Item Add to Cart")) {
            addItemResponse.setMessage(msg);
            addItemResponse.setStatus(true);
        } else {
            addItemResponse.setMessage("something went wrong");
            addItemResponse.setStatus(false);
        }
        return ResponseEntity.ok(addItemResponse);
    }

}
