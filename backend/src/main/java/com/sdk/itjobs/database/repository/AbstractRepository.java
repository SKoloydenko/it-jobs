package com.sdk.itjobs.database.repository;

import com.sdk.itjobs.database.entity.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {
}
