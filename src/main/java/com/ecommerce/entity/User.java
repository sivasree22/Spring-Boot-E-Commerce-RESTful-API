package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a registered user (customer or admin) in the e-commerce platform.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> cartItems;

    public User() {}

    public User(Long id, String name, String email, String password, String role, List<Order> orders, List<Cart> cartItems) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.orders = orders;
        this.cartItems = cartItems;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
    public List<Cart> getCartItems() { return cartItems; }
    public void setCartItems(List<Cart> cartItems) { this.cartItems = cartItems; }

    public static class UserBuilder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;
        private List<Order> orders;
        private List<Cart> cartItems;

        public UserBuilder id(Long id) { this.id = id; return this; }
        public UserBuilder name(String name) { this.name = name; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder password(String password) { this.password = password; return this; }
        public UserBuilder role(String role) { this.role = role; return this; }
        public UserBuilder orders(List<Order> orders) { this.orders = orders; return this; }
        public UserBuilder cartItems(List<Cart> cartItems) { this.cartItems = cartItems; return this; }

        public User build() {
            return new User(id, name, email, password, role, orders, cartItems);
        }
    }
}
