package com.sdk.itjobs.mapper.vacancy;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.database.entity.vacancy.FavouriteVacancy;
import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.FavouriteVacancyResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FavouriteVacancyMapper {
    private final VacancyMapper vacancyMapper;

    public FavouriteVacancy asEntity(Vacancy vacancy, User user) {
        return FavouriteVacancy.builder().vacancy(vacancy).user(user).build();
    }

    public FavouriteVacancyResponse asResponse(FavouriteVacancy favouriteVacancy) {
        return FavouriteVacancyResponse.builder()
                .id(favouriteVacancy.getId())
                .vacancy(vacancyMapper.asResponse(favouriteVacancy.getVacancy()))
                .build();
    }

    public PageResponse<FavouriteVacancyResponse> asPageResponse(Page<FavouriteVacancy> page) {
        return new PageResponse<>(
                page.stream().map(this::asResponse).collect(Collectors.toList()),
                (long) page.getNumber(),
                (long) page.getNumberOfElements(),
                (long) page.getTotalPages());
    }
}
