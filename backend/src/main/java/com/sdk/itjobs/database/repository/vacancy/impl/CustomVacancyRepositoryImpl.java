package com.sdk.itjobs.database.repository.vacancy.impl;

import com.sdk.itjobs.database.entity.vacancy.Vacancy;
import com.sdk.itjobs.database.repository.vacancy.CustomVacancyRepository;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomVacancyRepositoryImpl implements CustomVacancyRepository {
    private final EntityManager entityManager;

    @Override
    public Page<Vacancy> findByFilters(
            ProgrammingLanguage programmingLanguage,
            Long minSalary,
            Long maxSalary,
            Aggregator aggregator,
            Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vacancy> query = cb.createQuery(Vacancy.class);
        Root<Vacancy> root = query.from(Vacancy.class);

        List<Predicate> predicates = new ArrayList<>();
        if (programmingLanguage != null) {
            predicates.add(cb.equal(root.get("programmingLanguage"), programmingLanguage));
        }
        if (minSalary != null) {
            predicates.add(cb.greaterThan(root.get("minSalary"), minSalary));
        }
        if (maxSalary != null) {
            predicates.add(cb.lessThan(root.get("maxSalary"), maxSalary));
        }
        if (aggregator != null) {
            predicates.add(cb.equal(root.get("aggregator"), aggregator));
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));

        TypedQuery<Vacancy> vacancies = entityManager.createQuery(query);
        vacancies.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        vacancies.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Vacancy> countRoot = countQuery.from(Vacancy.class);

        List<Predicate> countPredicates = new ArrayList<>();
        if (programmingLanguage != null) {
            countPredicates.add(
                    cb.equal(countRoot.get("programmingLanguage"), programmingLanguage));
        }
        if (minSalary != null) {
            countPredicates.add(cb.greaterThan(countRoot.get("minSalary"), minSalary));
        }
        if (maxSalary != null) {
            countPredicates.add(cb.lessThan(countRoot.get("maxSalary"), maxSalary));
        }
        if (aggregator != null) {
            countPredicates.add(cb.equal(countRoot.get("aggregator"), aggregator));
        }

        countQuery.select(cb.count(countRoot)).where(countPredicates.toArray(new Predicate[0]));

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(vacancies.getResultList(), pageable, count);
    }
}
