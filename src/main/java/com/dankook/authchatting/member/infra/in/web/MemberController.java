package com.dankook.authchatting.member.infra.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dankook.authchatting.member.application.MemberService;
import com.dankook.authchatting.member.infra.in.web.response.MemberResponse;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@PreAuthorize("hasAuthority('MEMBER')")
public class MemberController {
    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<MemberResponse> me(
            @Parameter(hidden = true) Authentication authentication
    ) {
        return ResponseEntity.ok().body(
                new MemberResponse(memberService.getById(Long.parseLong(authentication.getName())))
        );
    }

}
