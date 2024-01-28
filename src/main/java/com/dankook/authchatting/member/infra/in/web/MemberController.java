package com.dankook.authchatting.member.infra.in.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dankook.authchatting.member.application.MemberService;
import com.dankook.authchatting.member.infra.in.web.response.MemberResponse;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> me(
            @Parameter(hidden = true) Authentication authentication
    ) {
        return ResponseEntity.ok().body(
                new MemberResponse(memberService.getById(UUID.fromString(authentication.getName())))
        );
    }
    
}
