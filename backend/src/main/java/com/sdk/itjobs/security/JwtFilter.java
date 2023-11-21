package com.sdk.itjobs.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdk.itjobs.dto.user.UserPrincipal;
import com.sdk.itjobs.exception.AppException;
import com.sdk.itjobs.exception.auth.CorruptedTokenException;
import com.sdk.itjobs.exception.auth.UnauthorizedException;
import com.sdk.itjobs.util.TokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;

    private final String prefix = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (isHeaderValid(header)) {
                SecurityContextHolder.getContext().setAuthentication(createAuthToken(header));
            }
            filterChain.doFilter(request, response);
        } catch (CorruptedTokenException exception) {
            writeResponseError(response, new UnauthorizedException());
        }
    }

    private void writeResponseError(HttpServletResponse response, AppException exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8.name());
        response.setStatus(exception.getStatus().value());
        String body = new ObjectMapper().writeValueAsString(exception);
        response.getWriter().print(body);
    }

    private boolean isHeaderValid(String header) {
        if (header == null) {
            return false;
        } else {
            return header.startsWith(prefix);
        }
    }

    private UsernamePasswordAuthenticationToken createAuthToken(String header) throws CorruptedTokenException {
        UserPrincipal principal = tokenManager.parseTokenPrincipal(header.replace(prefix, ""));
        return new UsernamePasswordAuthenticationToken(principal.userId(), null, null);
    }
}
