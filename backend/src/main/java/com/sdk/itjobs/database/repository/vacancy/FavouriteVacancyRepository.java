package com.sdk.itjobs.database.repository.vacancy;

import com.sdk.itjobs.database.entity.vacancy.FavouriteVacancy;
import com.sdk.itjobs.database.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteVacancyRepository extends AbstractRepository<FavouriteVacancy> {
    boolean existsByVacancyIdAndUserId(Long vacancyId, Long userId);
    Page<FavouriteVacancy> findByUserId(Long userId, Pageable pageable);
}
