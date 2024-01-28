package com.dankook.authchatting.member.infra.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.dankook.authchatting.member.domain.Member;
import com.dankook.authchatting.member.domain.MemberRepository;
import com.dankook.authchatting.member.exception.NotFoundMemberException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getById(UUID id) {
        return memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);
    }
}
