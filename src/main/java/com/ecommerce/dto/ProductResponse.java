package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.ecommerce.entity.Product;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "Response object containing product details")
public class ProductResponse {
    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;

    @Schema(description = "Name of the product", example = "iPhone 15 Pro")
    private String name;

    @Schema(description = "Detailed description of the product", example = "Apple's latest flagship smartphone")
    private String description;

    @Schema(description = "Price of the product", example = "999.99")
    private BigDecimal price;

    @Schema(description = "Available stock quantity", example = "50")
    private Integer stock;

    @Schema(description = "Product category", example = "Electronics")
    private String category;

    public ProductResponse() {}

    public ProductResponse(Long id, String name, String description, BigDecimal price, Integer stock, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static ProductResponse builder() {
        return new ProductResponse();
    }

    public static ProductResponse fromEntity(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
        );
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    // Minimal builder-like methods to support existing code if necessary
    public ProductResponse id(Long id) { this.id = id; return this; }
    public ProductResponse name(String name) { this.name = name; return this; }
    public ProductResponse description(String description) { this.description = description; return this; }
    public ProductResponse price(BigDecimal price) { this.price = price; return this; }
    public ProductResponse stock(Integer stock) { this.stock = stock; return this; }
    public ProductResponse category(String category) { this.category = category; return this; }
    public ProductResponse build() { return this; }
}
