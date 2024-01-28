package com.dankook.authchatting.member.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dankook.authchatting.member.domain.Member;
import com.dankook.authchatting.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member getById(UUID id) {
        return memberRepository.getById(id);
    }
}
