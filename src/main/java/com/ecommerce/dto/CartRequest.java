package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public CartRequest() {}

    public CartRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public static CartRequestBuilder builder() {
        return new CartRequestBuilder();
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public static class CartRequestBuilder {
        private Long productId;
        private Integer quantity;

        public CartRequestBuilder productId(Long productId) { this.productId = productId; return this; }
        public CartRequestBuilder quantity(Integer quantity) { this.quantity = quantity; return this; }

        public CartRequest build() {
            return new CartRequest(productId, quantity);
        }
    }
}
