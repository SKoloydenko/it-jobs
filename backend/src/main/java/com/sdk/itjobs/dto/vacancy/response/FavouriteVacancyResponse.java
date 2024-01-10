package com.sdk.itjobs.dto.vacancy.response;

import lombok.Builder;

@Builder
public record FavouriteVacancyResponse(Long id, VacancyPublicResponse vacancy) {}
