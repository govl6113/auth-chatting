package com.dankook.authchatting.auth.exception;

public class NotFoundAuthException extends RuntimeException {
    public NotFoundAuthException() {
        super("해당 auth가 존재하지 않습니다.");
    }
}
