package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.OrderException;
import com.decoder.ecommerce.model.Address;
import com.decoder.ecommerce.model.Order;
import com.decoder.ecommerce.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Address shippingAddress);
    Order findOrderById(Long orderId) throws OrderException;
    List<Order> userOrdreHistory(Long userId);
    Order placedOrder(Long orderId) throws  OrderException;
    Order confirmedOrder(Long orderId) throws OrderException;
    Order shippedOrder(Long orderId) throws OrderException;
    Order deliveredOrder(Long orderId) throws OrderException;
    Order caceledOrder(Long orderId) throws OrderException;
    List<Order> getAllOrders();
    void deleteOrder(Long orderId) throws OrderException;
}
