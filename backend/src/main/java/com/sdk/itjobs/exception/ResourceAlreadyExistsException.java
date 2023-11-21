package com.sdk.itjobs.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends AppException {
    public ResourceAlreadyExistsException(Class<?> clazz, String fieldName, Object fieldValue) {
        super(HttpStatus.CONFLICT, clazz.getSimpleName() + " with " + fieldName + " " + fieldValue + " already exists");
    }
}
