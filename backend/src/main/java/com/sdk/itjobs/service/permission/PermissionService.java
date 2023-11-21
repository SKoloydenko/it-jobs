package com.sdk.itjobs.service.permission;

import com.sdk.itjobs.exception.ForbiddenException;
import com.sdk.itjobs.exception.ResourceNotFoundException;

public interface PermissionService {
    void validateAdmin() throws ForbiddenException, ResourceNotFoundException;

    Long getPrincipal();
}
