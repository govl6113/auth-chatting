package com.dankook.authchatting.member.infra.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dankook.authchatting.member.exception.NotFoundMemberException;

@RestControllerAdvice
public class MemberExceptionController {

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<String> notFoundMemberException(NotFoundMemberException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
