package com.dankook.authchatting.member.domain;

public interface MemberRepository {
    Member save(Member member);

    Member getById(Long id);
}
