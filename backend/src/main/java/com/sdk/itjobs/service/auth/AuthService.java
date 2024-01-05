package com.sdk.itjobs.service.auth;

import com.sdk.itjobs.dto.auth.request.LoginRequest;
import com.sdk.itjobs.dto.auth.request.RegistrationRequest;
import com.sdk.itjobs.dto.auth.response.TokenResponse;
import com.sdk.itjobs.dto.user.response.UserResponse;
import com.sdk.itjobs.exception.ResourceAlreadyExistsException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.exception.auth.CorruptedTokenException;
import com.sdk.itjobs.exception.auth.IncorrectPasswordException;

import org.springframework.data.util.Pair;

public interface AuthService {
    UserResponse register(RegistrationRequest request) throws ResourceAlreadyExistsException;

    Pair<TokenResponse, String> login(LoginRequest request)
            throws ResourceNotFoundException, IncorrectPasswordException;

    Pair<TokenResponse, String> refresh(String refreshToken)
            throws CorruptedTokenException, ResourceNotFoundException;

    void logout(String refreshToken);
}
