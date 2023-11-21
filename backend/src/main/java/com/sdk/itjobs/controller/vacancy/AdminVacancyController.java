package com.sdk.itjobs.controller.vacancy;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sdk.itjobs.util.constant.UrlConstants.API_V1_ADMIN;

@RestController
@RequestMapping(API_V1_ADMIN + "/vacancy")
@RequiredArgsConstructor
public class AdminVacancyController {
}
