package com.sdk.itjobs.service.permission.impl;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.exception.ForbiddenException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.service.permission.PermissionService;
import com.sdk.itjobs.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final UserService userService;

    @Override
    public void validateAdmin() throws ForbiddenException, ResourceNotFoundException {
        User user = userService.findEntityById(getPrincipal());
        if (!user.isAdmin()) {
            throw new ForbiddenException();
        }
    }

    @Override
    public Long getPrincipal() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return Long.parseLong(principal);
    }
}
