package com.ecommerce.loader;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Seeds the database with sample products and users at application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ProductRepository productRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedUsers();
        seedProducts();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .name("Admin User")
                    .email("admin@ecommerce.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ROLE_ADMIN")
                    .build();

            User user1 = User.builder()
                    .name("Alice Smith")
                    .email("alice@example.com")
                    .password(passwordEncoder.encode("user123"))
                    .role("ROLE_USER")
                    .build();

            User user2 = User.builder()
                    .name("Bob Jones")
                    .email("bob@example.com")
                    .password(passwordEncoder.encode("user123"))
                    .role("ROLE_USER")
                    .build();

            userRepository.saveAll(Arrays.asList(admin, user1, user2));
            System.out.println(">>> DataInitializer: Seeded 3 users (1 ADMIN, 2 USERS).");
        }
    }

    private void seedProducts() {
        if (productRepository.count() == 0) {
            List<Product> products = Arrays.asList(
                Product.builder().name("iPhone 15 Pro").description("Apple's latest flagship smartphone with Titanium design").price(new BigDecimal("999.00")).stock(50).category("Electronics").build(),
                Product.builder().name("MacBook Air M2").description("Thinnest 13-inch laptop with M2 chip").price(new BigDecimal("1199.00")).stock(30).category("Electronics").build(),
                Product.builder().name("Sony WH-1000XM5").description("Best-in-class noise cancelling headphones").price(new BigDecimal("349.00")).stock(100).category("Accessories").build(),
                Product.builder().name("Kindle Paperwhite").description("High-resolution display e-reader").price(new BigDecimal("139.00")).stock(200).category("Books").build(),
                Product.builder().name("Nike Air Max 270").description("Comfortable lifestyle sneakers").price(new BigDecimal("150.00")).stock(80).category("Apparel").build(),
                Product.builder().name("Levi's 501 Original").description("Classic straight leg jeans").price(new BigDecimal("69.00")).stock(150).category("Apparel").build(),
                Product.builder().name("Logitech MX Master 3S").description("Standard-setting workflow mouse").price(new BigDecimal("99.00")).stock(120).category("Accessories").build(),
                Product.builder().name("Dell UltraSharp 27").description("4K professional monitor").price(new BigDecimal("599.00")).stock(40).category("Electronics").build(),
                Product.builder().name("Aeropress Coffee Maker").description("Great cup of coffee anywhere").price(new BigDecimal("40.00")).stock(300).category("Home & Kitchen").build(),
                Product.builder().name("Yeti Rambler 30oz").description("Heavy duty insulated tumbler").price(new BigDecimal("38.00")).stock(500).category("Home & Kitchen").build()
            );

            productRepository.saveAll(products);
            System.out.println(">>> DataInitializer: Seeded 10 products.");
        }
    }
}
