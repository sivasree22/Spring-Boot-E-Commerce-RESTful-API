package com.ecommerce.service;

import com.ecommerce.dto.OrderResponse;
import java.util.List;

/**
 * Service contract for Order management — placing, tracking, and list orders.
 */
public interface OrderService {

    OrderResponse placeOrder(Long userId, String shippingAddress);

    OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getOrdersByUser(Long userId);

    OrderResponse updateOrderStatus(Long orderId, String status);

    void cancelOrder(Long orderId);
}
