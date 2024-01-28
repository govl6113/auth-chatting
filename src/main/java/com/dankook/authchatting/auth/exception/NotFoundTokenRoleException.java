package com.dankook.authchatting.auth.exception;

public class NotFoundTokenRoleException extends RuntimeException {
    public NotFoundTokenRoleException() {
        super("해당 token이 존재하지 않습니다.");
    }
}
