package com.sdk.itjobs.service.vacancy.common.impl;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.vacancy.VacancyRepository;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyResponse;
import com.sdk.itjobs.mapper.vacancy.VacancyMapper;
import com.sdk.itjobs.service.vacancy.common.VacancyService;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Override
    public PageResponse<VacancyResponse> list(ProgrammingLanguage programmingLanguage, Pageable pageable) {
        Page<Vacancy> page;
        if (programmingLanguage == null) {
            page = vacancyRepository.findAll(pageable);
        } else {
            page = vacancyRepository.findByProgrammingLanguage(programmingLanguage, pageable);
        }
        return vacancyMapper.asPageResponse(page);
    }
}
