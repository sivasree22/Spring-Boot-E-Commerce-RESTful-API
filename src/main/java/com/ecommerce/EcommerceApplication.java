package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the E-Commerce RESTful API application.
 *
 * <p>This Spring Boot application provides a comprehensive set of REST endpoints
 * for managing products, categories, users, orders, and payments in an
 * e-commerce context.</p>
 */
@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
