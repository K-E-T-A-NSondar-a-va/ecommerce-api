package com.decoder.ecommerce.implementation;

import com.decoder.ecommerce.exception.OrderException;
import com.decoder.ecommerce.exception.UserException;
import com.decoder.ecommerce.model.*;
import com.decoder.ecommerce.repository.*;
import com.decoder.ecommerce.service.CartService;
import com.decoder.ecommerce.service.OrderService;
import com.decoder.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
//    @Autowired
//    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public PurchaseOrder createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);

        Address address = addressRepository.save(shippingAddress);
        user.getAddresses().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem item:cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);
        }

        PurchaseOrder createdOrder = new PurchaseOrder();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedprice());
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setTotalItems(cart.getTotalItem());

        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreatedAt(LocalDateTime.now());

        PurchaseOrder savedOrder = orderRepository.save(createdOrder);

        for(OrderItem item : orderItems) {
            item.setPurchaseOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    @Override
    public PurchaseOrder findOrderById(Long orderId) throws OrderException {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderException("Order not exists with id: "+orderId));
    }

    @Override
    public List<PurchaseOrder> userOrdreHistory(Long userId) throws UserException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("user with "+userId+" might not exists"));
        return orderRepository.findByUser(user);
    }

    @Override
    public PurchaseOrder placedOrder(Long orderId) throws OrderException {
        PurchaseOrder purchaseOrder = findOrderById(orderId);
        purchaseOrder.setOrderStatus("PLACED");
        purchaseOrder.getPaymentDetails().setStatus("COMPLETED");
        return purchaseOrder;
    }

    @Override
    public PurchaseOrder confirmedOrder(Long orderId) throws OrderException {
        PurchaseOrder purchaseOrder = findOrderById(orderId);
        purchaseOrder.setOrderStatus("CONFIRMED");
        return orderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder shippedOrder(Long orderId) throws OrderException {
        PurchaseOrder purchaseOrder = findOrderById(orderId);
        purchaseOrder.setOrderStatus("SHIPPED");
        return orderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder deliveredOrder(Long orderId) throws OrderException {
        PurchaseOrder purchaseOrder = findOrderById(orderId);
        purchaseOrder.setOrderStatus("DELIVERED");
        return orderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder caceledOrder(Long orderId) throws OrderException {
        PurchaseOrder purchaseOrder = findOrderById(orderId);
        purchaseOrder.setOrderStatus("CANCELED");
        return orderRepository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        PurchaseOrder order = orderRepository.findById(orderId).orElseThrow(() -> new OrderException("order with id "+orderId+" not exists"));
        orderRepository.delete(order);
    }
}
