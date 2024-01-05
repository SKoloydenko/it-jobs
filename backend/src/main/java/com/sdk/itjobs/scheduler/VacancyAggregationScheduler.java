package com.sdk.itjobs.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdk.itjobs.service.vacancy.hh.HHVacancyAggregationService;
import com.sdk.itjobs.service.vacancy.superjob.SuperJobVacancyAggregationService;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyAggregationScheduler {

    private final HHVacancyAggregationService hhVacancyAggregationService;
    private final SuperJobVacancyAggregationService superJobVacancyAggregationService;

    @Scheduled(cron = "0 0/30 * * * ?")
    void aggregate() {
        List<ProgrammingLanguage> programmingLanguages =
                new ArrayList<>(Arrays.asList(ProgrammingLanguage.values()));
        programmingLanguages.forEach(
                programmingLanguage -> {
                    try {
                        hhVacancyAggregationService.aggregate(programmingLanguage);
                        superJobVacancyAggregationService.aggregate(programmingLanguage);
                    } catch (JsonProcessingException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
