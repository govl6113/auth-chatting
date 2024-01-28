package com.dankook.authchatting.auth.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dankook.authchatting.auth.domain.Auth;
import com.dankook.authchatting.auth.domain.AuthRepository;
import com.dankook.authchatting.auth.exception.InvalidTokenException;
import com.dankook.authchatting.auth.exception.WrongPasswordException;
import com.dankook.authchatting.auth.infra.in.web.request.ReissueRequest;
import com.dankook.authchatting.auth.infra.in.web.request.SignInRequest;
import com.dankook.authchatting.auth.infra.in.web.request.SignUpRequest;
import com.dankook.authchatting.member.domain.Member;
import com.dankook.authchatting.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Auth signUp(SignUpRequest request) {
        Member member = memberRepository.save(
                Member.builder()
                      .name(request.getName())
                      .build()
        );

        return authRepository.save(
                Auth.builder()
                    .account(request.getAccount())
                    .password(request.getPassword())
                    .member(member)
                    .encoder(passwordEncoder)
                    .build()
        );
    }

    @Override
    public Auth signIn(SignInRequest request) {
        Auth auth = authRepository.getByAccount(request.getAccount());

        if (!passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
            throw new WrongPasswordException();
        }
        return auth.updateToken(tokenProvider.create(auth));
    }

    @Override
    public Auth reissue(ReissueRequest request) {
        if (!tokenProvider.validation(request.getRefreshToken())) {
            throw new InvalidTokenException();
        }

        Auth auth = authRepository.getByMemberId(tokenProvider.parseMemberId(request.getRefreshToken()));

        return auth.updateToken(tokenProvider.create(auth));
    }
}
