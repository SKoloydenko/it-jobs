package com.sdk.itjobs.service.vacancy.common.impl;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.vacancy.FavouriteVacancyRepository;
import com.sdk.itjobs.database.repository.vacancy.VacancyRepository;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyPublicResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyUserResponse;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.mapper.vacancy.VacancyMapper;
import com.sdk.itjobs.service.vacancy.common.VacancyService;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyMapper vacancyMapper;
    private final VacancyRepository vacancyRepository;
    private final FavouriteVacancyRepository favouriteVacancyRepository;

    @Override
    public Vacancy findEntityById(Long id) throws ResourceNotFoundException {
        return vacancyRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Vacancy.class, "id", id));
    }

    @Override
    public PageResponse<VacancyPublicResponse> listForPublic(
            ProgrammingLanguage programmingLanguage,
            Long minSalary,
            Long maxSalary,
            Aggregator aggregator,
            Pageable pageable) {
        Page<Vacancy> page =
                vacancyRepository.findByFilters(
                        programmingLanguage, minSalary, maxSalary, aggregator, pageable);
        return vacancyMapper.asPublicPageResponse(page);
    }

    @Override
    public PageResponse<VacancyUserResponse> listForUser(
            Long userId,
            ProgrammingLanguage programmingLanguage,
            Long minSalary,
            Long maxSalary,
            Aggregator aggregator,
            Pageable pageable) {
        Page<Vacancy> page =
                vacancyRepository.findByFilters(
                        programmingLanguage, minSalary, maxSalary, aggregator, pageable);
        List<VacancyUserResponse> vacancies =
                page.stream()
                        .map(
                                vacancy -> {
                                    Boolean favourite =
                                            favouriteVacancyRepository.existsByVacancyIdAndUserId(
                                                    vacancy.getId(), userId);
                                    return vacancyMapper.asUserResponse(vacancy, favourite);
                                })
                        .collect(Collectors.toList());
        return vacancyMapper.asUserPageResponse(vacancies, page);
    }
}
