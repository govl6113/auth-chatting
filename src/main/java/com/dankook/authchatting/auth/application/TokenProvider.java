package com.dankook.authchatting.auth.application;

import java.util.UUID;

import org.springframework.security.core.Authentication;

import com.dankook.authchatting.auth.domain.Auth;
import com.dankook.authchatting.auth.domain.Token;

public interface TokenProvider {
    Token create(Auth auth);

    Authentication getAuthentication(String accessToken);

    boolean validation(String token);

    UUID parseMemberId(String token);
}
