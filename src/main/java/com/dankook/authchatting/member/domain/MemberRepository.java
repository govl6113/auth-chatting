package com.dankook.authchatting.member.domain;

import java.util.UUID;

public interface MemberRepository {
    Member save(Member member);

    Member getById(UUID id);
}
