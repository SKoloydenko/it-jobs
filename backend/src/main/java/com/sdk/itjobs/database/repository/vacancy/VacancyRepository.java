package com.sdk.itjobs.database.repository.vacancy;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.AbstractRepository;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends AbstractRepository<Vacancy>, PagingAndSortingRepository<Vacancy, Long> {
    Page<Vacancy> findByProgrammingLanguage(ProgrammingLanguage programmingLanguage, Pageable pageable);

    Boolean existsByExternalIdAndAggregator(Long externalId, Aggregator aggregator);
}
