package com.decoder.ecommerce.implementation;

import com.decoder.ecommerce.exception.OrderException;
import com.decoder.ecommerce.model.Address;
import com.decoder.ecommerce.model.PurchaseOrder;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.repository.CartItemRepository;
import com.decoder.ecommerce.repository.CartRepository;
import com.decoder.ecommerce.service.OrderService;
import com.decoder.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductService productService;

    @Override
    public PurchaseOrder createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public PurchaseOrder findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<PurchaseOrder> userOrdreHistory(Long userId) {
        return null;
    }

    @Override
    public PurchaseOrder placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public PurchaseOrder confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public PurchaseOrder shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public PurchaseOrder deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public PurchaseOrder caceledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<PurchaseOrder> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
