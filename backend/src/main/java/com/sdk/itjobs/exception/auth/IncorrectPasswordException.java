package com.sdk.itjobs.exception.auth;

import com.sdk.itjobs.exception.AppException;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends AppException {
    public IncorrectPasswordException() {
        super(HttpStatus.UNAUTHORIZED, "Incorrect password");
    }
}
