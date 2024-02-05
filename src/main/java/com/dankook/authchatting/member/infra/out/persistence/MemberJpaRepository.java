package com.dankook.authchatting.member.infra.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dankook.authchatting.member.domain.Member;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
