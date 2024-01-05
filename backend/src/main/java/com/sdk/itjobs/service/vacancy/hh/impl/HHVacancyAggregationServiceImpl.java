package com.sdk.itjobs.service.vacancy.hh.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdk.itjobs.client.HHClient;
import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.vacancy.VacancyRepository;
import com.sdk.itjobs.dto.client.response.HHClientResponse;
import com.sdk.itjobs.mapper.vacancy.VacancyMapper;
import com.sdk.itjobs.service.vacancy.hh.HHVacancyAggregationService;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HHVacancyAggregationServiceImpl implements HHVacancyAggregationService {
    private static final Logger logger = LoggerFactory.getLogger(HHVacancyAggregationService.class);

    private final HHClient hhClient;
    private final ObjectMapper objectMapper;
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Override
    public void aggregate(ProgrammingLanguage programmingLanguage)
            throws JsonProcessingException, InterruptedException {
        filter(programmingLanguage, getClientResponse(programmingLanguage));
    }

    private List<HHClientResponse> getClientResponse(ProgrammingLanguage programmingLanguage)
            throws JsonProcessingException, InterruptedException {
        long page = 0L;
        Long maxPage = null;
        final Long pageSize = 100L;

        List<HHClientResponse> vacancies = new ArrayList<>();
        do {
            logger.info(
                    "Requesting vacancies for "
                            + programmingLanguage.name()
                            + " from hh.ru on page "
                            + page
                            + " with max page "
                            + maxPage);
            String response = hhClient.getVacancies(programmingLanguage.name(), page, pageSize);
            vacancies.addAll(parseJsonResponse(response));
            page++;
            if (maxPage == null) {
                maxPage = getMaxPage(response);
            }
            Thread.sleep(1000);
        } while (page < maxPage);
        return vacancies;
    }

    private List<HHClientResponse> parseJsonResponse(String response)
            throws JsonProcessingException {
        String items = objectMapper.readTree(response).get("items").toString();
        return objectMapper.readValue(items, new TypeReference<>() {});
    }

    private Long getMaxPage(String response) throws JsonProcessingException {
        return Long.parseLong(objectMapper.readTree(response).get("pages").toString());
    }

    private void filter(ProgrammingLanguage programmingLanguage, List<HHClientResponse> vacancies) {
        vacancies.stream()
                .filter(
                        vacancy ->
                                vacancy.getTitle()
                                                .toLowerCase()
                                                .contains(programmingLanguage.name().toLowerCase())
                                        && !vacancyRepository.existsByExternalIdAndAggregator(
                                                vacancy.getId(), Aggregator.HH))
                .forEach(
                        vacancy -> {
                            Vacancy entity = vacancyMapper.asEntity(programmingLanguage, vacancy);
                            vacancyRepository.save(entity);
                        });
    }
}
