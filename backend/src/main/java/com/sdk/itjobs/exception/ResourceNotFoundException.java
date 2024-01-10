package com.sdk.itjobs.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(Class<?> clazz, String fieldName, Object fieldValue) {
        super(
                HttpStatus.NOT_FOUND,
                clazz.getSimpleName() + " with " + fieldName + " " + fieldValue + " not found");
    }

    public ResourceNotFoundException(
            Class<?> clazz,
            String fieldName1,
            Object fieldValue1,
            String fieldName2,
            Object fieldValue2) {
        super(
                HttpStatus.NOT_FOUND,
                clazz.getSimpleName()
                        + " with "
                        + fieldName1
                        + " "
                        + fieldValue1
                        + " and "
                        + fieldName2
                        + " "
                        + fieldValue2
                        + " not found");
    }
}
