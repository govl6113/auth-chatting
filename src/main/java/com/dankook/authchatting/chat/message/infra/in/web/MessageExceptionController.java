package com.dankook.authchatting.chat.message.infra.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dankook.authchatting.chat.message.exception.NotFoundMessageException;

@RestControllerAdvice

public class MessageExceptionController {
    @ExceptionHandler(NotFoundMessageException.class)
    public ResponseEntity<String> notFoundMessageException(NotFoundMessageException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
