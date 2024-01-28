package com.dankook.authchatting.auth.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("비밀번호가 맞지 않습니다.");
    }
}
