package com.sdk.itjobs.service.auth.impl;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.database.repository.user.UserRepository;
import com.sdk.itjobs.dto.auth.request.LoginRequest;
import com.sdk.itjobs.dto.auth.request.RegistrationRequest;
import com.sdk.itjobs.dto.auth.response.TokenResponse;
import com.sdk.itjobs.dto.user.response.UserResponse;
import com.sdk.itjobs.exception.ResourceAlreadyExistsException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.exception.auth.CorruptedTokenException;
import com.sdk.itjobs.exception.auth.IncorrectPasswordException;
import com.sdk.itjobs.mapper.user.UserMapper;
import com.sdk.itjobs.service.auth.AuthService;
import com.sdk.itjobs.util.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;

    @Override
    public UserResponse register(RegistrationRequest request) throws ResourceAlreadyExistsException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException(User.class, "email", request.getEmail());
        }
        String passwordHash = passwordEncoder.encode(request.getPassword());
        User user = userMapper.asEntity(request, passwordHash);
        userRepository.save(user);
        return userMapper.asResponse(user);
    }

    @Override
    public Pair<TokenResponse, String> login(LoginRequest request) throws ResourceNotFoundException, IncorrectPasswordException {
        User user = findEntityByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IncorrectPasswordException();
        }
        return tokenManager.getTokensPair(user);
    }

    @Override
    public Pair<TokenResponse, String> refresh(String refreshToken) throws CorruptedTokenException, ResourceNotFoundException {
        return tokenManager.refreshTokensPair(refreshToken);
    }

    @Override
    public void logout(String refreshToken) {
        tokenManager.deleteRefreshToken(refreshToken);
    }

    private User findEntityByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(User.class, "email", email));
    }
}
