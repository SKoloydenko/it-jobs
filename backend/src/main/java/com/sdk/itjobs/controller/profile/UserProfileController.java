package com.sdk.itjobs.controller.profile;

import static com.sdk.itjobs.util.constant.UrlConstants.API_V1_USER;

import com.sdk.itjobs.dto.user.response.UserResponse;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.service.permission.PermissionService;
import com.sdk.itjobs.service.user.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_V1_USER + "/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserService userService;
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getSelf() throws ResourceNotFoundException {
        UserResponse response = userService.findById(permissionService.getPrincipal());
        return ResponseEntity.ok().body(response);
    }
}
