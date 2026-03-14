package com.ecommerce.dto;

import com.ecommerce.entity.Cart;
import java.math.BigDecimal;

public class CartItemResponse {
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public CartItemResponse() {}

    public CartItemResponse(Long id, Long productId, String productName, Integer quantity, BigDecimal unitPrice, BigDecimal subtotal) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public static CartItemResponse builder() {
        return new CartItemResponse();
    }

    public static CartItemResponse fromEntity(Cart cart) {
        BigDecimal price = cart.getProduct().getPrice();
        Integer qty = cart.getQuantity();
        return new CartItemResponse(
                cart.getId(),
                cart.getProduct().getId(),
                cart.getProduct().getName(),
                qty,
                price,
                price.multiply(BigDecimal.valueOf(qty))
        );
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public CartItemResponse id(Long id) { this.id = id; return this; }
    public CartItemResponse productId(Long productId) { this.productId = productId; return this; }
    public CartItemResponse productName(String productName) { this.productName = productName; return this; }
    public CartItemResponse quantity(Integer quantity) { this.quantity = quantity; return this; }
    public CartItemResponse unitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; return this; }
    public CartItemResponse subtotal(BigDecimal subtotal) { this.subtotal = subtotal; return this; }
    public CartItemResponse build() { return this; }
}
