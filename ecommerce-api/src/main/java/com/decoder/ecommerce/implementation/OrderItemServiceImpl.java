package com.decoder.ecommerce.implementation;

import com.decoder.ecommerce.model.OrderItem;
import com.decoder.ecommerce.repository.OrderItemRepository;
import com.decoder.ecommerce.repository.OrderRepository;
import com.decoder.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
