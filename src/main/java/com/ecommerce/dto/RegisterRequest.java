package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request object for user registration")
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(description = "User's password", example = "password123")
    private String password;

    public RegisterRequest() {}

    public RegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static RegisterRequestBuilder builder() {
        return new RegisterRequestBuilder();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public static class RegisterRequestBuilder {
        private String name;
        private String email;
        private String password;

        public RegisterRequestBuilder name(String name) { this.name = name; return this; }
        public RegisterRequestBuilder email(String email) { this.email = email; return this; }
        public RegisterRequestBuilder password(String password) { this.password = password; return this; }

        public RegisterRequest build() {
            return new RegisterRequest(name, email, password);
        }
    }
}
