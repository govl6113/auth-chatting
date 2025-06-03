package com.dankook.authchatting.member.application;

import com.dankook.authchatting.member.domain.Member;

public interface MemberService {
    Member getById(Long id);
}
