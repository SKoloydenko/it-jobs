package com.sdk.itjobs.dto.auth.response;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken) {
}
