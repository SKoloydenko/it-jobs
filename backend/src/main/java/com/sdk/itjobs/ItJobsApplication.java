package com.sdk.itjobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ItJobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItJobsApplication.class, args);
    }
}
