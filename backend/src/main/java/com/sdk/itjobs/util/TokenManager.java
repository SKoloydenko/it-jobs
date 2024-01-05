package com.sdk.itjobs.util;

import com.sdk.itjobs.database.entity.token.RefreshToken;
import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.database.repository.token.RefreshTokenRepository;
import com.sdk.itjobs.dto.auth.response.TokenResponse;
import com.sdk.itjobs.dto.user.UserPrincipal;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.exception.auth.CorruptedTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

@Component
@RequiredArgsConstructor
public class TokenManager {

    @Value("${spring.security.jwt.secret}")
    private final String secret = "pberHN5P24RDKmRphgtQEwV7IlSzTtDT";

    @Value("${spring.security.jwt.access.lifetime}")
    private final long accessTokenLifetime = 24;

    @Value("${spring.security.jwt.refresh.lifetime}")
    private final long refreshTokenLifetime = 336;

    private final RefreshTokenRepository refreshTokenRepository;

    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

    @Transactional
    public Pair<TokenResponse, String> getTokensPair(User user) {
        String accessToken = generateAccessToken(user.getId());
        String refreshToken = generateRefreshToken(user);
        TokenResponse response = TokenResponse.builder().accessToken(accessToken).build();
        return Pair.of(response, refreshToken);
    }

    @Transactional
    public Pair<TokenResponse, String> refreshTokensPair(String refreshToken)
            throws ResourceNotFoundException, CorruptedTokenException {
        RefreshToken token =
                refreshTokenRepository
                        .findByToken(refreshToken)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                RefreshToken.class, "token", refreshToken));
        if (token.getExpiresAt().before(Date.from(Instant.now()))) {
            throw new CorruptedTokenException();
        }
        return getTokensPair(token.getUser());
    }

    public UserPrincipal parseTokenPrincipal(String token) throws CorruptedTokenException {
        Jws<Claims> claims = parseToken(token);
        try {
            Long userId = Long.parseLong(claims.getPayload().get("userId").toString());
            return new UserPrincipal(userId);
        } catch (Exception e) {
            throw new CorruptedTokenException();
        }
    }

    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByToken(refreshToken);
    }

    private Date getExpirationDate(Long lifetime) {
        return Date.from(Instant.now().plus(lifetime, ChronoUnit.DAYS));
    }

    private String generateAccessToken(Long userId) {
        Date expiration = getExpirationDate(accessTokenLifetime);
        return Jwts.builder()
                .claim("userId", userId)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    private String generateRefreshToken(User user) {
        refreshTokenRepository.deleteByUserId(user.getId());
        Date expiration = getExpirationDate(refreshTokenLifetime);
        RefreshToken refreshToken =
                RefreshToken.builder()
                        .user(user)
                        .expiresAt(expiration)
                        .token(UUID.randomUUID().toString())
                        .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    private Jws<Claims> parseToken(String token) throws CorruptedTokenException {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
        } catch (JwtException throwable) {
            throw new CorruptedTokenException();
        }
    }
}
