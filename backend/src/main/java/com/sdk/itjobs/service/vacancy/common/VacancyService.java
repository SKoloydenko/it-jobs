package com.sdk.itjobs.service.vacancy.common;

import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyResponse;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
import org.springframework.data.domain.Pageable;

public interface VacancyService {
    PageResponse<VacancyResponse> list(ProgrammingLanguage programmingLanguage, Pageable pageable);
}
