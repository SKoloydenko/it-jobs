package com.sdk.itjobs.database.repository.user;

import com.sdk.itjobs.database.entity.user.User;
import com.sdk.itjobs.database.repository.AbstractRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
