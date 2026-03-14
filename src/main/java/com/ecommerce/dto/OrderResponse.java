package com.ecommerce.dto;

import com.ecommerce.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {
    private Long id;
    private BigDecimal totalPrice;
    private String orderStatus;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    public OrderResponse() {}

    public OrderResponse(Long id, BigDecimal totalPrice, String orderStatus, LocalDateTime createdAt, List<OrderItemResponse> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.items = items;
    }

    public static OrderResponseBuilder builder() {
        return new OrderResponseBuilder();
    }

    public static class OrderItemResponse {
        private Long productId;
        private String productName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subtotal;

        public OrderItemResponse() {}

        public OrderItemResponse(Long productId, String productName, Integer quantity, BigDecimal unitPrice, BigDecimal subtotal) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.subtotal = subtotal;
        }

        public static OrderItemResponseBuilder builder() {
            return new OrderItemResponseBuilder();
        }

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

        public static class OrderItemResponseBuilder {
            private Long productId;
            private String productName;
            private Integer quantity;
            private BigDecimal unitPrice;
            private BigDecimal subtotal;

            public OrderItemResponseBuilder productId(Long productId) { this.productId = productId; return this; }
            public OrderItemResponseBuilder productName(String productName) { this.productName = productName; return this; }
            public OrderItemResponseBuilder quantity(Integer quantity) { this.quantity = quantity; return this; }
            public OrderItemResponseBuilder unitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; return this; }
            public OrderItemResponseBuilder subtotal(BigDecimal subtotal) { this.subtotal = subtotal; return this; }

            public OrderItemResponse build() {
                return new OrderItemResponse(productId, productName, quantity, unitPrice, subtotal);
            }
        }
    }

    public static OrderResponse fromEntity(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getTotalPrice(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(item -> new OrderItemResponse(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getPrice(),
                                item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                        ))
                        .collect(Collectors.toList())
        );
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrderItemResponse> getItems() { return items; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }

    public static class OrderResponseBuilder {
        private Long id;
        private BigDecimal totalPrice;
        private String orderStatus;
        private LocalDateTime createdAt;
        private List<OrderItemResponse> items;

        public OrderResponseBuilder id(Long id) { this.id = id; return this; }
        public OrderResponseBuilder totalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; return this; }
        public OrderResponseBuilder orderStatus(String orderStatus) { this.orderStatus = orderStatus; return this; }
        public OrderResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public OrderResponseBuilder items(List<OrderItemResponse> items) { this.items = items; return this; }

        public OrderResponse build() {
            return new OrderResponse(id, totalPrice, orderStatus, createdAt, items);
        }
    }
}
