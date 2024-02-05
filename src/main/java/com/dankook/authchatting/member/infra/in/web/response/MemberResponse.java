package com.dankook.authchatting.member.infra.in.web.response;

import com.dankook.authchatting.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    Long id;
    String name;

    @Builder
    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }
}
