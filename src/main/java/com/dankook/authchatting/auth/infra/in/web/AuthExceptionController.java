package com.dankook.authchatting.auth.infra.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dankook.authchatting.auth.exception.InvalidTokenException;
import com.dankook.authchatting.auth.exception.NotFoundAuthException;
import com.dankook.authchatting.auth.exception.NotFoundTokenRoleException;
import com.dankook.authchatting.auth.exception.WrongPasswordException;

@RestControllerAdvice
public class AuthExceptionController {

    @ExceptionHandler(NotFoundAuthException.class)
    public ResponseEntity<String> notFoundAuthException(NotFoundAuthException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundTokenRoleException.class)
    public ResponseEntity<String> notFoundTokenRoleException(NotFoundTokenRoleException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> wrongPasswordException(WrongPasswordException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> invalidTokenException(InvalidTokenException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
