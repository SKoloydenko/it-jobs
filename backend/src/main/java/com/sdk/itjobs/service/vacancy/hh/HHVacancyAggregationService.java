package com.sdk.itjobs.service.vacancy.hh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;

public interface HHVacancyAggregationService {
    void aggregate(ProgrammingLanguage programmingLanguage)
            throws JsonProcessingException, InterruptedException;
}
