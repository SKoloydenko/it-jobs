package com.sdk.itjobs.service.vacancy.favourite.impl;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.database.entity.vacancy.FavouriteVacancy;
import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.vacancy.FavouriteVacancyRepository;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.FavouriteVacancyResponse;
import com.sdk.itjobs.exception.ResourceAlreadyExistsException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.mapper.vacancy.FavouriteVacancyMapper;
import com.sdk.itjobs.service.user.UserService;
import com.sdk.itjobs.service.vacancy.common.VacancyService;
import com.sdk.itjobs.service.vacancy.favourite.FavouriteVacancyService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavouriteVacancyServiceImpl implements FavouriteVacancyService {
    private final FavouriteVacancyMapper favouriteVacancyMapper;
    private final FavouriteVacancyRepository favouriteVacancyRepository;
    private final UserService userService;
    private final VacancyService vacancyService;

    @Override
    public PageResponse<FavouriteVacancyResponse> list(Long userId, Pageable pageable) {
        Page<FavouriteVacancy> page = favouriteVacancyRepository.findByUserId(userId, pageable);
        return favouriteVacancyMapper.asPageResponse(page);
    }

    @Override
    public FavouriteVacancyResponse createFavouriteVacancy(Long vacancyId, Long userId)
            throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if (favouriteVacancyRepository.existsByVacancyIdAndUserId(vacancyId, userId)) {
            throw new ResourceAlreadyExistsException(
                    FavouriteVacancy.class, "vacancy id", vacancyId, "user id", userId);
        }

        Vacancy vacancy = vacancyService.findEntityById(vacancyId);
        User user = userService.findEntityById(userId);

        FavouriteVacancy favouriteVacancy = favouriteVacancyMapper.asEntity(vacancy, user);
        favouriteVacancyRepository.save(favouriteVacancy);

        return favouriteVacancyMapper.asResponse(favouriteVacancy);
    }

    @Override
    @Transactional
    public void deleteFavouriteVacancy(Long vacancyId, Long userId)
            throws ResourceNotFoundException {
        if (!favouriteVacancyRepository.existsByVacancyIdAndUserId(vacancyId, userId)) {
            throw new ResourceNotFoundException(
                    FavouriteVacancy.class, "vacancy id", vacancyId, "user id", userId);
        }

        favouriteVacancyRepository.deleteByVacancyIdAndUserId(vacancyId, userId);
    }
}
