package com.dankook.authchatting.member.application;

import java.util.UUID;

import com.dankook.authchatting.member.domain.Member;

public interface MemberService {
    Member getById(UUID id);
}
