package com.sdk.itjobs.service.vacancy.superjob;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdk.itjobs.client.SuperJobClient;
import com.sdk.itjobs.config.SuperJobConfig;
import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.vacancy.VacancyRepository;
import com.sdk.itjobs.dto.client.response.SuperJobClientResponse;
import com.sdk.itjobs.mapper.vacancy.VacancyMapper;
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
public class SuperJobVacancyAggregationServiceImpl implements SuperJobVacancyAggregationService {
    private static final Logger logger =
            LoggerFactory.getLogger(SuperJobVacancyAggregationService.class);

    private final SuperJobClient superJobClient;
    private final SuperJobConfig superJobConfig;
    private final ObjectMapper objectMapper;
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Override
    public void aggregate(ProgrammingLanguage programmingLanguage)
            throws JsonProcessingException, InterruptedException {
        filter(programmingLanguage, getClientResponse(programmingLanguage));
    }

    private List<SuperJobClientResponse> getClientResponse(ProgrammingLanguage programmingLanguage)
            throws JsonProcessingException, InterruptedException {
        long page = 0L;
        Long maxPage = 10L;
        final Long pageSize = 20L;

        List<SuperJobClientResponse> vacancies = new ArrayList<>();
        do {
            logger.info(
                    "Requesting vacancies for "
                            + programmingLanguage.name()
                            + " superjob.ru on page "
                            + page
                            + " with max page "
                            + maxPage);
            String response =
                    superJobClient.getVacancies(
                            superJobConfig.getSecretKey(),
                            programmingLanguage.name(),
                            page,
                            pageSize);
            vacancies.addAll(parseJsonResponse(response));
            page++;
            if (maxPage == null) {
                maxPage = getMaxPage(response);
            }
            Thread.sleep(2000);
        } while (page < maxPage);
        return vacancies;
    }

    private List<SuperJobClientResponse> parseJsonResponse(String response)
            throws JsonProcessingException {
        String objects = objectMapper.readTree(response).get("objects").toString();
        return objectMapper.readValue(objects, new TypeReference<>() {});
    }

    private Long getMaxPage(String response) throws JsonProcessingException {
        return Long.parseLong(objectMapper.readTree(response).get("total").toString());
    }

    private void filter(
            ProgrammingLanguage programmingLanguage, List<SuperJobClientResponse> vacancies) {
        vacancies.stream()
                .filter(
                        vacancy ->
                                vacancy.getTitle()
                                                .toLowerCase()
                                                .contains(programmingLanguage.name().toLowerCase())
                                        && !vacancyRepository.existsByExternalIdAndAggregator(
                                                vacancy.getId(), Aggregator.SUPERJOB))
                .forEach(
                        vacancy -> {
                            Vacancy entity = vacancyMapper.asEntity(programmingLanguage, vacancy);
                            vacancyRepository.save(entity);
                        });
    }
}
