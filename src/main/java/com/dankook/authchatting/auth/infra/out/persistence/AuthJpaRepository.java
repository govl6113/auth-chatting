package com.dankook.authchatting.auth.infra.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dankook.authchatting.auth.domain.Auth;

public interface AuthJpaRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByAccount(String account);

    Optional<Auth> findByMemberId(Long memberId);
}
