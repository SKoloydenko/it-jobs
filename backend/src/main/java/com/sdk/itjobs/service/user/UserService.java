package com.sdk.itjobs.service.user;


import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.dto.user.response.UserResponse;
import com.sdk.itjobs.exception.ResourceNotFoundException;

public interface UserService {
    User findEntityById(Long id) throws ResourceNotFoundException;

    UserResponse findById(Long id) throws ResourceNotFoundException;
}
