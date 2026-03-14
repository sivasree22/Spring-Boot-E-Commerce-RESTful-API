package com.ecommerce.service.impl;

import com.ecommerce.dto.CartItemResponse;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/** Concrete implementation of {@link CartService}. */
@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemResponse> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).stream()
                .map(CartItemResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemResponse addItemToCart(Long userId, Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        User user = new User();
        user.setId(userId);

        Cart cartItem = cartRepository.findByUserIdAndProductId(userId, productId)
                .map(item -> {
                    item.setQuantity(item.getQuantity() + quantity);
                    return item;
                })
                .orElseGet(() -> Cart.builder()
                        .user(user)
                        .product(product)
                        .quantity(quantity)
                        .build());

        return CartItemResponse.fromEntity(cartRepository.save(cartItem));
    }

    @Override
    public CartItemResponse updateItemQuantity(Long userId, Long productId, Integer quantity) {
        Cart item = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "productId", productId));
        item.setQuantity(quantity);
        return CartItemResponse.fromEntity(cartRepository.save(item));
    }

    @Override
    public CartItemResponse removeItemFromCart(Long userId, Long productId) {
        Cart item = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "productId", productId));
        cartRepository.delete(item);
        return CartItemResponse.fromEntity(item);
    }

    @Override
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
