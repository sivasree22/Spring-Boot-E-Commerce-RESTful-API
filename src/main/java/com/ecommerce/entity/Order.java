package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a placed order by a User containing one or more OrderItems.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal totalPrice;

    private String orderStatus;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {}

    public Order(Long id, User user, BigDecimal totalPrice, String orderStatus, LocalDateTime createdAt, List<OrderItem> orderItems) {
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public static class OrderBuilder {
        private Long id;
        private User user;
        private BigDecimal totalPrice;
        private String orderStatus;
        private LocalDateTime createdAt;
        private List<OrderItem> orderItems;

        public OrderBuilder id(Long id) { this.id = id; return this; }
        public OrderBuilder user(User user) { this.user = user; return this; }
        public OrderBuilder totalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; return this; }
        public OrderBuilder orderStatus(String orderStatus) { this.orderStatus = orderStatus; return this; }
        public OrderBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public OrderBuilder orderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; return this; }

        public Order build() {
            return new Order(id, user, totalPrice, orderStatus, createdAt, orderItems);
        }
    }
}
