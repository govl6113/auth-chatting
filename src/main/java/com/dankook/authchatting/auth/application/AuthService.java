package com.dankook.authchatting.auth.application;

import com.dankook.authchatting.auth.domain.Auth;
import com.dankook.authchatting.auth.infra.in.web.request.ReissueRequest;
import com.dankook.authchatting.auth.infra.in.web.request.SignInRequest;
import com.dankook.authchatting.auth.infra.in.web.request.SignUpRequest;

public interface AuthService {
    Auth signUp(SignUpRequest request);

    Auth signIn(SignInRequest request);

    Auth reissue(ReissueRequest request);
}
