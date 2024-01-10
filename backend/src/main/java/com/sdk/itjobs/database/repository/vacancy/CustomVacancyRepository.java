package com.sdk.itjobs.database.repository.vacancy;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomVacancyRepository {
    Page<Vacancy> findByFilters(
            ProgrammingLanguage programmingLanguage,
            Long minSalary,
            Long maxSalary,
            Aggregator aggregator,
            Pageable pageable);
}
