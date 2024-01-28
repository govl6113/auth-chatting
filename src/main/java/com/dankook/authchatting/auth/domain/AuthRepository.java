package com.dankook.authchatting.auth.domain;

import java.util.UUID;

public interface AuthRepository {
    Auth save(Auth auth);

    Auth getById(UUID id);

    Auth getByAccount(String account);

    Auth getByMemberId(UUID memberId);
}
