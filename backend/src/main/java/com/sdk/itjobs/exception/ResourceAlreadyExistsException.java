package com.sdk.itjobs.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends AppException {
    public ResourceAlreadyExistsException(Class<?> clazz, String fieldName, Object fieldValue) {
        super(HttpStatus.CONFLICT, clazz.getSimpleName() + " with " + fieldName + " " + fieldValue + " already exists");
    }

    public ResourceAlreadyExistsException(Class<?> clazz, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2) {
        super(HttpStatus.CONFLICT, clazz.getSimpleName() + " with " + fieldName1 + " " + fieldValue1 + " and " + fieldName2 + " " + fieldValue2 + " already exists");
    }
}
