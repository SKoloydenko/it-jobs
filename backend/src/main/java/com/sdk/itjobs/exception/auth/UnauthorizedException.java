package com.sdk.itjobs.exception.auth;

import com.sdk.itjobs.exception.AppException;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AppException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
}
