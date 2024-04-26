package com.decoder.ecommerce.controller;

import com.decoder.ecommerce.exception.OrderException;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.Address;
import com.decoder.ecommerce.model.PurchaseOrder;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.service.OrderService;
import com.decoder.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByJwt(jwt);
        PurchaseOrder purchaseOrder = orderService.createOrder(user, shippingAddress);
        return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<PurchaseOrder>> userOrderHisoty(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByJwt(jwt);
        List<PurchaseOrder> purchaseOrders = orderService.userOrdreHistory(user.getId());
        return new ResponseEntity<>(purchaseOrders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> findOrderById(@PathVariable("id") Long orderId, @RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user = userService.findUserByJwt(jwt);
        PurchaseOrder order = orderService.findOrderById(orderId);
        return ResponseEntity.ok(order);
    }


}
