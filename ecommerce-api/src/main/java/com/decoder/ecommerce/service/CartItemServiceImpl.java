package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.CartItemException;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.Cart;
import com.decoder.ecommerce.model.CartItem;
import com.decoder.ecommerce.model.Product;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.repository.CartItemRepository;
import com.decoder.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(cartItem.getUserId());

        if(user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExists(cart,product,size,userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId()))
            cartItemRepository.deleteById(cartItemId);
        else
            throw new UserException("you can't remove another users item");
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> optional = cartItemRepository.findById(cartItemId);
        return optional.orElseThrow(() -> new CartItemException("CartItem not found with id: "+cartItemId));
    }
}
