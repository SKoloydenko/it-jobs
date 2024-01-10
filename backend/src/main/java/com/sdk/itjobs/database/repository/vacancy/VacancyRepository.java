package com.sdk.itjobs.database.repository.vacancy;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.AbstractRepository;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository
        extends AbstractRepository<Vacancy>,
                PagingAndSortingRepository<Vacancy, Long>,
                CustomVacancyRepository {
    Boolean existsByExternalIdAndAggregator(Long externalId, Aggregator aggregator);
}
