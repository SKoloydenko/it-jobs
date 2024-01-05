package com.sdk.itjobs.controller.auth;

import com.sdk.itjobs.dto.AppMessage;
import com.sdk.itjobs.dto.auth.request.LoginRequest;
import com.sdk.itjobs.dto.auth.request.RegistrationRequest;
import com.sdk.itjobs.dto.auth.response.TokenResponse;
import com.sdk.itjobs.dto.user.response.UserResponse;
import com.sdk.itjobs.exception.ResourceAlreadyExistsException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.exception.auth.CorruptedTokenException;
import com.sdk.itjobs.exception.auth.IncorrectPasswordException;
import com.sdk.itjobs.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sdk.itjobs.util.constant.UrlConstants.API_V1_PUBLIC;
import static com.sdk.itjobs.util.constant.UrlConstants.REFRESH_TOKEN_COOKIE;

@RestController
@RequestMapping(API_V1_PUBLIC + "/auth")
@RequiredArgsConstructor
public class PublicAuthController {

    private final AuthService authService;
    private final Long refreshTokenLifetime = 336L;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) throws ResourceAlreadyExistsException {
        UserResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) throws ResourceNotFoundException, IncorrectPasswordException {
        Pair<TokenResponse, String> pair = authService.login(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, generateRefreshCookie(pair.getSecond(), refreshTokenLifetime * 60 * 60))
                .body(pair.getFirst());
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(REFRESH_TOKEN_COOKIE) String cookie) throws ResourceNotFoundException, CorruptedTokenException {
        Pair<TokenResponse, String> pair = authService.refresh(cookie);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, generateRefreshCookie(pair.getSecond(), refreshTokenLifetime * 60 * 60))
                .body(pair.getFirst());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(REFRESH_TOKEN_COOKIE) String cookie) {
        authService.logout(cookie);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, generateRefreshCookie("", 0L))
                .body(new AppMessage("You have successfully logged out"));
    }

    private String generateRefreshCookie(String token, Long maxAge) {
        return REFRESH_TOKEN_COOKIE + "=" + token + ";Path=/;Max-Age=" + maxAge + ";HttpOnly";
    }
}
