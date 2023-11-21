package com.sdk.itjobs.dto.vacancy.response;

import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
import lombok.Builder;

@Builder
public record VacancyResponse(
        Long id,
        ProgrammingLanguage programmingLanguage,
        String title,
        Long minSalary,
        Long maxSalary,
        String employer,
        String url,
        Long externalId,
        Aggregator aggregator
) {
}
