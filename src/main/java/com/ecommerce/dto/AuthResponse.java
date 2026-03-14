package com.ecommerce.dto;


public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String email;
    private String role;

    public AuthResponse() {}

    public AuthResponse(String token, String type, Long userId, String email, String role) {
        this.token = token;
        this.type = type;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public static class AuthResponseBuilder {
        private String token;
        private String type = "Bearer";
        private Long userId;
        private String email;
        private String role;

        public AuthResponseBuilder token(String token) { this.token = token; return this; }
        public AuthResponseBuilder type(String type) { this.type = type; return this; }
        public AuthResponseBuilder userId(Long userId) { this.userId = userId; return this; }
        public AuthResponseBuilder email(String email) { this.email = email; return this; }
        public AuthResponseBuilder role(String role) { this.role = role; return this; }

        public AuthResponse build() {
            return new AuthResponse(token, type, userId, email, role);
        }
    }
}
