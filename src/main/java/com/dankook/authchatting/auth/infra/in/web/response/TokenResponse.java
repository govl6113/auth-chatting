package com.dankook.authchatting.auth.infra.in.web.response;

import com.dankook.authchatting.auth.domain.Token;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponse {
    String accessToken;
    String refreshToken;

    @Builder
    public TokenResponse(Token token) {
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
    }
}
