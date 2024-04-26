package com.decoder.ecommerce.controller;

import com.decoder.ecommerce.exception.OrderException;
import com.decoder.ecommerce.model.PurchaseOrder;
import com.decoder.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
//@SecurityRequirement(name = "authBearer")  // controller level security swagger.
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    @Operation(
            tags = "GET Purchase Order",
            description = "Get all purchase order by user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "data not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<List<PurchaseOrder>> getAllOrdersHandler() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<PurchaseOrder> confirmedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException {
        return ResponseEntity.ok(orderService.confirmedOrder(orderId));
    }

    @PutMapping("/{orderId}/placed")
    public ResponseEntity<PurchaseOrder> placeOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException {
        return ResponseEntity.ok(orderService.placedOrder(orderId));
    }

    @PutMapping("/{orderId}/shipped")
    public ResponseEntity<PurchaseOrder> shippedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException {
        return ResponseEntity.ok(orderService.shippedOrder(orderId));
    }

    @PutMapping("/{orderId}/delivered")
    public ResponseEntity<PurchaseOrder> deliveredOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException {
        return ResponseEntity.ok(orderService.deliveredOrder(orderId));
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<PurchaseOrder> cancelOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException {
        return ResponseEntity.ok(orderService.caceledOrder(orderId));
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<String> deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted");
    }
}
