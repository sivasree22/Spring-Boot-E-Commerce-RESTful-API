package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderResponse;
import com.ecommerce.entity.*;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.*;
import com.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/** Concrete implementation of {@link OrderService}. */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public OrderResponse placeOrder(Long userId, String shippingAddress) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<Cart> cartItems = cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cannot place an order with an empty cart");
        }

        Order order = Order.builder()
                .user(user)
                .totalPrice(BigDecimal.ZERO)
                .orderStatus("PENDING")
                .createdAt(java.time.LocalDateTime.now())
                .orderItems(new java.util.ArrayList<>())
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct();

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .price(product.getPrice())
                    .build();
            order.getOrderItems().add(orderItem);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        order.setTotalPrice(total);
        Order saved = orderRepository.save(order);

        // Clear cart after successful order
        cartRepository.deleteByUserId(userId);

        return OrderResponse.fromEntity(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {
        return OrderResponse.fromEntity(findOrThrow(orderId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUser(Long userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream().map(OrderResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, String status) {
        Order order = findOrThrow(orderId);
        order.setOrderStatus(status);
        return OrderResponse.fromEntity(orderRepository.save(order));
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = findOrThrow(orderId);
        if ("DELIVERED".equals(order.getOrderStatus())) {
            throw new IllegalStateException("Cannot cancel a delivered order");
        }
        order.setOrderStatus("CANCELLED");
        orderRepository.save(order);
    }

    // ---------------------------------------------------------------- helpers
    private Order findOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
    }
}
