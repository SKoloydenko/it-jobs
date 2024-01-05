package com.sdk.itjobs.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "properties.superjob")
public class SuperJobConfig {
    private String secretKey;
}
