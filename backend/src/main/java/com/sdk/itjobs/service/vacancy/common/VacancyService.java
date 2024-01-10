package com.sdk.itjobs.service.vacancy.common;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyPublicResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyUserResponse;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

import org.springframework.data.domain.Pageable;

public interface VacancyService {
    Vacancy findEntityById(Long id) throws ResourceNotFoundException;

    PageResponse<VacancyPublicResponse> listForPublic(
            ProgrammingLanguage programmingLanguage,
            Long minSalary,
            Long maxSalary,
            Aggregator aggregator,
            Pageable pageable);

    PageResponse<VacancyUserResponse> listForUser(
            Long userId,
            ProgrammingLanguage programmingLanguage,
            Long minSalary,
            Long maxSalary,
            Aggregator aggregator,
            Pageable pageable);
}
