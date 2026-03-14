package com.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    public Cart() {}

    public Cart(Long id, User user, Product product, Integer quantity) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public static CartBuilder builder() {
        return new CartBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public static class CartBuilder {
        private Long id;
        private User user;
        private Product product;
        private Integer quantity;

        public CartBuilder id(Long id) { this.id = id; return this; }
        public CartBuilder user(User user) { this.user = user; return this; }
        public CartBuilder product(Product product) { this.product = product; return this; }
        public CartBuilder quantity(Integer quantity) { this.quantity = quantity; return this; }

        public Cart build() {
            return new Cart(id, user, product, quantity);
        }
    }
}
