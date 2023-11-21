package com.sdk.itjobs.dto.auth.request;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
