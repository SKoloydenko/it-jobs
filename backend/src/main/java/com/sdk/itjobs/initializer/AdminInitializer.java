package com.sdk.itjobs.initializer;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.database.repository.user.UserRepository;
import com.sdk.itjobs.util.constant.enumeration.UserRole;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        String adminEmail = "admin@mail.ru";
        if (!userRepository.existsByEmail(adminEmail)) {
            String passwordHash = passwordEncoder.encode("password");
            User admin =
                    User.builder()
                            .email(adminEmail)
                            .passwordHash(passwordHash)
                            .role(UserRole.ADMIN)
                            .build();
            userRepository.save(admin);
        }
    }
}
