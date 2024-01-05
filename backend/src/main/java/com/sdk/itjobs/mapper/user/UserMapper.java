package com.sdk.itjobs.mapper.user;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.dto.auth.request.RegistrationRequest;
import com.sdk.itjobs.dto.user.response.UserResponse;
import com.sdk.itjobs.util.constant.enumeration.UserRole;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User asEntity(RegistrationRequest request, String passwordHash) {
        return User.builder()
                .email(request.getEmail())
                .passwordHash(passwordHash)
                .role(UserRole.USER)
                .build();
    }

    public UserResponse asResponse(User user) {
        return UserResponse.builder().email(user.getEmail()).build();
    }
}
