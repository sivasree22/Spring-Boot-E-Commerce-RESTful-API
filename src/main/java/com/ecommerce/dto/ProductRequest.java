package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "Request object for creating or updating a product")
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    @Schema(description = "Name of the product", example = "iPhone 15 Pro")
    private String name;

    @Schema(description = "Detailed description of the product", example = "Apple's latest flagship smartphone")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Schema(description = "Price of the product", example = "999.99")
    private BigDecimal price;

    @Min(value = 0, message = "Stock cannot be negative")
    @Schema(description = "Available stock quantity", example = "50")
    private Integer stock;

    @Schema(description = "Product category", example = "Electronics")
    private String category;

    public ProductRequest() {}

    public ProductRequest(String name, String description, BigDecimal price, Integer stock, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static ProductRequestBuilder builder() {
        return new ProductRequestBuilder();
    }

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

    public static class ProductRequestBuilder {
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;
        private String category;

        public ProductRequestBuilder name(String name) { this.name = name; return this; }
        public ProductRequestBuilder description(String description) { this.description = description; return this; }
        public ProductRequestBuilder price(BigDecimal price) { this.price = price; return this; }
        public ProductRequestBuilder stock(Integer stock) { this.stock = stock; return this; }
        public ProductRequestBuilder category(String category) { this.category = category; return this; }

        public ProductRequest build() {
            return new ProductRequest(name, description, price, stock, category);
        }
    }
}
