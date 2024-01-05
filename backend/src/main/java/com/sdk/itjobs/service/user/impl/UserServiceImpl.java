package com.sdk.itjobs.service.user.impl;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.database.repository.user.UserRepository;
import com.sdk.itjobs.dto.user.response.UserResponse;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.mapper.user.UserMapper;
import com.sdk.itjobs.service.user.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findEntityById(Long id) throws ResourceNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, "id", id));
    }

    @Override
    public UserResponse findById(Long id) throws ResourceNotFoundException {
        User user = findEntityById(id);
        return userMapper.asResponse(user);
    }
}
