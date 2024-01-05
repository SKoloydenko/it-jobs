package com.sdk.itjobs.exception.auth;

import com.sdk.itjobs.exception.AppException;

import org.springframework.http.HttpStatus;

public class CorruptedTokenException extends AppException {
    public CorruptedTokenException() {
        super(HttpStatus.UNAUTHORIZED, "Corrupted token");
    }
}
