package com.dankook.authchatting.auth.domain;

public interface AuthRepository {
    Auth save(Auth auth);

    Auth getById(Long id);

    Auth getByAccount(String account);

    Auth getByMemberId(Long memberId);
}
