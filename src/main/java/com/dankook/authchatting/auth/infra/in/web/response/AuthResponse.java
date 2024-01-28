package com.dankook.authchatting.auth.infra.in.web.response;

import java.util.UUID;

import com.dankook.authchatting.auth.domain.Auth;
import com.dankook.authchatting.member.infra.in.web.response.MemberResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    UUID id;
    MemberResponse member;
    TokenResponse token;

    @Builder
    public AuthResponse(Auth auth) {
        this.id = auth.getId();
        this.member = MemberResponse.builder().member(auth.getMember()).build();
        this.token = auth.getToken() != null ? TokenResponse.builder().token(auth.getToken()).build() : null;
    }
}
