package com.sdk.itjobs.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends AppException {
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "Forbidden");
    }
}
