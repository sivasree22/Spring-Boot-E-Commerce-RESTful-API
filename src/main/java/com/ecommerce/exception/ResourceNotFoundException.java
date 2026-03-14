package com.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception thrown when a requested resource cannot be found by its identifier.
 * Maps to HTTP 404 Not Found.
 */
@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
