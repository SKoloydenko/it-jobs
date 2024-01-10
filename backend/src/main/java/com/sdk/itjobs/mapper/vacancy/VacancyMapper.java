package com.sdk.itjobs.mapper.vacancy;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.client.response.HHClientResponse;
import com.sdk.itjobs.dto.client.response.SuperJobClientResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyPublicResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyUserResponse;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VacancyMapper {
    public Vacancy asEntity(ProgrammingLanguage programmingLanguage, HHClientResponse request) {
        String minSalary = request.getMinSalary();
        String maxSalary = request.getMaxSalary();
        return Vacancy.builder()
                .programmingLanguage(programmingLanguage)
                .title(request.getTitle())
                .minSalary(minSalary != null ? Long.parseLong(minSalary) : null)
                .maxSalary(maxSalary != null ? Long.parseLong(maxSalary) : null)
                .employer(request.getEmployer())
                .url(request.getUrl())
                .externalId(request.getId())
                .aggregator(Aggregator.HH)
                .build();
    }

    public Vacancy asEntity(
            ProgrammingLanguage programmingLanguage, SuperJobClientResponse request) {
        String minSalary = request.getMinSalary();
        String maxSalary = request.getMaxSalary();
        return Vacancy.builder()
                .programmingLanguage(programmingLanguage)
                .title(request.getTitle())
                .minSalary(minSalary != null ? Long.parseLong(minSalary) : null)
                .maxSalary(maxSalary != null ? Long.parseLong(maxSalary) : null)
                .employer(request.getEmployer())
                .url(request.getUrl())
                .externalId(request.getId())
                .aggregator(Aggregator.SUPERJOB)
                .build();
    }

    public VacancyPublicResponse asPublicResponse(Vacancy vacancy) {
        return VacancyPublicResponse.builder()
                .id(vacancy.getId())
                .programmingLanguage(vacancy.getProgrammingLanguage())
                .title(vacancy.getTitle())
                .minSalary(vacancy.getMinSalary())
                .maxSalary(vacancy.getMaxSalary())
                .employer(vacancy.getEmployer())
                .url(vacancy.getUrl())
                .externalId(vacancy.getExternalId())
                .aggregator(vacancy.getAggregator())
                .build();
    }

    public VacancyUserResponse asUserResponse(Vacancy vacancy, Boolean favourite) {
        return VacancyUserResponse.builder()
                .id(vacancy.getId())
                .programmingLanguage(vacancy.getProgrammingLanguage())
                .title(vacancy.getTitle())
                .minSalary(vacancy.getMinSalary())
                .maxSalary(vacancy.getMaxSalary())
                .employer(vacancy.getEmployer())
                .url(vacancy.getUrl())
                .externalId(vacancy.getExternalId())
                .aggregator(vacancy.getAggregator())
                .favourite(favourite)
                .build();
    }

    public PageResponse<VacancyPublicResponse> asPublicPageResponse(Page<Vacancy> page) {
        return new PageResponse<>(
                page.stream().map(this::asPublicResponse).collect(Collectors.toList()),
                (long) page.getNumber(),
                (long) page.getNumberOfElements(),
                (long) page.getTotalPages());
    }

    public PageResponse<VacancyUserResponse> asUserPageResponse(
            List<VacancyUserResponse> vacancies, Page<Vacancy> page) {
        return new PageResponse<>(
                vacancies,
                (long) page.getNumber(),
                (long) page.getNumberOfElements(),
                (long) page.getTotalPages());
    }
}
