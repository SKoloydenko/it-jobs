package com.sdk.itjobs.service.vacancy.favourite;

import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.FavouriteVacancyResponse;
import com.sdk.itjobs.exception.ResourceAlreadyExistsException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

public interface FavouriteVacancyService {
    PageResponse<FavouriteVacancyResponse> list(Long userId, Pageable pageable);
    FavouriteVacancyResponse createFavouriteVacancy(Long vacancyId, Long userId) throws ResourceAlreadyExistsException, ResourceNotFoundException;
    void deleteFavouriteVacancy(Long id) throws ResourceNotFoundException;
}
