package com.sdk.itjobs.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handle(Throwable throwable) {
        if (throwable instanceof AppException) {
            return ((AppException) throwable).asResponse();
        }
        return new InternalServerError(throwable).asResponse();
    }
}
