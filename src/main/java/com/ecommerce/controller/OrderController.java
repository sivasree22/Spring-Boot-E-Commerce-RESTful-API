package com.ecommerce.controller;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.dto.OrderResponse;
import com.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for order placement and order history.
 */
@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "Order placement and management")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Place a new order from the current user's cart")
    public ResponseEntity<OrderResponse> placeOrder(@RequestParam(defaultValue = "1") Long userId,
                                                         @RequestBody(required = false) OrderRequest request) {
        String shippingAddress = request != null ? request.getShippingAddress() : "Default Address";
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(userId, shippingAddress));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order details by ID")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    @Operation(summary = "Get all orders for the current user")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@RequestParam(defaultValue = "1") Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}
