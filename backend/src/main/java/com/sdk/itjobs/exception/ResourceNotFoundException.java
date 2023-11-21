package com.sdk.itjobs.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(Class<?> clazz, String fieldName, Object fieldValue) {
        super(HttpStatus.NOT_FOUND, clazz.getSimpleName() + " with " + fieldName + " " + fieldValue + " not found");
    }
}
