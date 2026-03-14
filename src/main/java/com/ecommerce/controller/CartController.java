package com.ecommerce.controller;

import com.ecommerce.dto.CartItemResponse;
import com.ecommerce.dto.CartRequest;
import com.ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for shopping cart operations scoped to a specific user.
 */
@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "Shopping cart management")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Operation(summary = "View current user's cart")
    public ResponseEntity<List<CartItemResponse>> getCart(@RequestParam(defaultValue = "1") Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/add")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<CartItemResponse> addItem(@RequestParam(defaultValue = "1") Long userId,
                                                     @Valid @RequestBody CartRequest request) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, request.getProductId(), request.getQuantity()));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Remove item from cart")
    public ResponseEntity<CartItemResponse> removeItem(@RequestParam(defaultValue = "1") Long userId,
                                                        @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }
}
