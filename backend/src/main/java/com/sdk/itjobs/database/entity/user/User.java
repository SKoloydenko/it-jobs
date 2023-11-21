package com.sdk.itjobs.database.entity.user;

import com.sdk.itjobs.database.entity.AbstractCreatedAtEntity;
import com.sdk.itjobs.util.constant.enumeration.UserRole;
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

import static com.sdk.itjobs.util.constant.JPAConstants.emailLength;
import static com.sdk.itjobs.util.constant.JPAConstants.passwordHashLength;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
final public class User extends AbstractCreatedAtEntity {
    @Column(nullable = false, length = emailLength, unique = true)
    private String email;

    @Column(nullable = false, length = passwordHashLength)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }
}
