package com.dankook.authchatting.auth.infra.out.persistence;

import org.springframework.stereotype.Repository;

import com.dankook.authchatting.auth.domain.Auth;
import com.dankook.authchatting.auth.domain.AuthRepository;
import com.dankook.authchatting.auth.exception.NotFoundAuthException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthJpaRepository authRepository;

    @Override
    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }

    @Override
    public Auth getById(Long id) {
        return authRepository.findById(id).orElseThrow(NotFoundAuthException::new);
    }

    @Override
    public Auth getByAccount(String account) {
        return authRepository.findByAccount(account).orElseThrow(NotFoundAuthException::new);
    }

    @Override
    public Auth getByMemberId(Long memberId) {
        return authRepository.findByMemberId(memberId).orElseThrow(NotFoundAuthException::new);
    }
}
