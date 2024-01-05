package com.sdk.itjobs.controller.vacancy;

import static com.sdk.itjobs.util.constant.UrlConstants.API_V1_PUBLIC;

import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyResponse;
import com.sdk.itjobs.service.vacancy.common.VacancyService;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_V1_PUBLIC + "/vacancy")
@RequiredArgsConstructor
public class PublicVacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(value = "programmingLanguage", required = false)
                    ProgrammingLanguage programmingLanguage,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageResponse<VacancyResponse> vacancies =
                vacancyService.list(programmingLanguage, PageRequest.of(page - 1, size));
        return ResponseEntity.ok().body(vacancies);
    }
}
