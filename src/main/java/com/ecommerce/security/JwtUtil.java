package com.ecommerce.security;

// TODO: Add io.jsonwebtoken:jjwt-api dependency to pom.xml when JWT is implemented.
//       Recommended: io.jsonwebtoken:jjwt-api, jjwt-impl, jjwt-jackson @ 0.12.x

import org.springframework.stereotype.Component;

/**
 * Utility class for JWT token operations.
 *
 * <p>Responsibilities (to be implemented):</p>
 * <ul>
 *   <li>Generate a signed JWT from a UserDetails principal</li>
 *   <li>Validate and parse incoming JWT tokens</li>
 *   <li>Extract the username / email claim from a token</li>
 *   <li>Check token expiry</li>
 * </ul>
 *
 * <p>Expected fields once implemented:</p>
 * <pre>
 * {@code @Value("${app.jwt.secret}")} private String jwtSecret;
 * {@code @Value("${app.jwt.expiration-ms}")} private long jwtExpirationMs;
 * </pre>
 */
@Component
public class JwtUtil {

    // TODO: implement generateToken(UserDetails), validateToken(String, UserDetails),
    //       extractUsername(String), isTokenExpired(String)

    public String generateToken(String email) {
        throw new UnsupportedOperationException("JWT not yet implemented — add JJWT dependency first");
    }

    public boolean validateToken(String token) {
        throw new UnsupportedOperationException("JWT not yet implemented");
    }

    public String extractUsername(String token) {
        throw new UnsupportedOperationException("JWT not yet implemented");
    }
}
