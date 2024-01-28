package com.dankook.authchatting.auth.infra.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dankook.authchatting.auth.domain.Auth;

public interface AuthJpaRepository extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByAccount(String account);

    Optional<Auth> findByMemberId(UUID memberId);
}
