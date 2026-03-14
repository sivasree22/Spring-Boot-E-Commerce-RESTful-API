package com.ecommerce.exception;

/**
 * Custom exception thrown when a request is invalid or violates business rules.
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
