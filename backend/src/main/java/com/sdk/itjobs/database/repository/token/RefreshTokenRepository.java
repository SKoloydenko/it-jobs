package com.sdk.itjobs.database.repository.token;

import com.sdk.itjobs.database.entity.token.RefreshToken;
import com.sdk.itjobs.database.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends AbstractRepository<RefreshToken> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(Long userId);

    void deleteByToken(String token);
}
