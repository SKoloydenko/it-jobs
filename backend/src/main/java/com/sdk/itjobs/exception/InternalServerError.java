package com.sdk.itjobs.exception;

import org.springframework.http.HttpStatus;

public class InternalServerError extends AppException {
    public InternalServerError(Throwable throwable) {
        super(
                HttpStatus.INTERNAL_SERVER_ERROR,
                throwable.getMessage() != null ? throwable.getMessage() : "Internal server error");
    }
}
