package com.sdk.itjobs.service.vacancy.superjob;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

public interface SuperJobVacancyAggregationService {
    void aggregate(ProgrammingLanguage programmingLanguage)
            throws JsonProcessingException, InterruptedException;
}
