package com.sdk.itjobs.dto.auth.request;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;
}
