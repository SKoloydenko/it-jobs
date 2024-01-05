package com.sdk.itjobs.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"localizedMessage", "cause", "stackTrace", "ourStackTrace", "suppressed"})
public class AppException extends Exception {
    private HttpStatus status;

    private String message;

    public AppException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss[.SSS]")
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ResponseEntity<?> asResponse() {
        printStackTrace();
        return ResponseEntity.status(status).body(this);
    }
}
