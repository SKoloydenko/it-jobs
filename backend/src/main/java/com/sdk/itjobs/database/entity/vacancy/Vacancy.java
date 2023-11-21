package com.sdk.itjobs.database.entity.vacancy;

import com.sdk.itjobs.database.entity.AbstractCreatedAtEntity;
import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacancy")
public class Vacancy extends AbstractCreatedAtEntity {
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;

    @Column(nullable = false, length = 100)
    private String title;

    @Column
    private Long minSalary;

    @Column
    private Long maxSalary;

    @Column(nullable = false, length = 200)
    private String employer;

    @Column(nullable = false, length = 200)
    private String url;

    @Column(nullable = false)
    private Long externalId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Aggregator aggregator;
}
