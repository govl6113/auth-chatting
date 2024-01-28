package com.dankook.authchatting.auth.infra.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dankook.authchatting.auth.application.AuthService;
import com.dankook.authchatting.auth.infra.in.web.request.ReissueRequest;
import com.dankook.authchatting.auth.infra.in.web.request.SignInRequest;
import com.dankook.authchatting.auth.infra.in.web.request.SignUpRequest;
import com.dankook.authchatting.auth.infra.in.web.response.AuthResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(new AuthResponse(authService.signUp(request)));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(new AuthResponse(authService.signIn(request)));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Boolean> logout() {
        return ResponseEntity.ok(true);
    }

    @PostMapping("/reissue")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody ReissueRequest request) {
        return ResponseEntity.ok(new AuthResponse(authService.reissue(request)));
    }

}
