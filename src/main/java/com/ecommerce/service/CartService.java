package com.ecommerce.service;

import com.ecommerce.dto.CartItemResponse;
import java.util.List;

/**
 * Service contract for Cart operations — view, add, and remove items.
 */
public interface CartService {

    List<CartItemResponse> getCartByUserId(Long userId);

    CartItemResponse addItemToCart(Long userId, Long productId, Integer quantity);

    CartItemResponse updateItemQuantity(Long userId, Long productId, Integer quantity);

    CartItemResponse removeItemFromCart(Long userId, Long productId);

    void clearCart(Long userId);
}
