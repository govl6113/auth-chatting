package com.dankook.authchatting.member.exception;

public class NotFoundMemberException extends RuntimeException {
    public NotFoundMemberException() {
        super("해당하는 member가 존재하지 않습니다.");
    }
}
