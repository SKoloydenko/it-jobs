package com.sdk.itjobs.controller.vacancy;

import static com.sdk.itjobs.util.constant.UrlConstants.API_V1_ADMIN;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_V1_ADMIN + "/vacancy")
@RequiredArgsConstructor
public class AdminVacancyController {}
