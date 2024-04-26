package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.OrderException;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.Address;
import com.decoder.ecommerce.model.PurchaseOrder;
import com.decoder.ecommerce.model.User;

import java.util.List;

public interface OrderService {
    PurchaseOrder createOrder(User user, Address shippingAddress);
    PurchaseOrder findOrderById(Long orderId) throws OrderException;
    List<PurchaseOrder> userOrdreHistory(Long userId) throws UserException;
    PurchaseOrder placedOrder(Long orderId) throws  OrderException;
    PurchaseOrder confirmedOrder(Long orderId) throws OrderException;
    PurchaseOrder shippedOrder(Long orderId) throws OrderException;
    PurchaseOrder deliveredOrder(Long orderId) throws OrderException;
    PurchaseOrder caceledOrder(Long orderId) throws OrderException;
    List<PurchaseOrder> getAllOrders();
    void deleteOrder(Long orderId) throws OrderException;
}
