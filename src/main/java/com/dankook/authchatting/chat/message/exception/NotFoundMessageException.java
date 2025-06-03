package com.dankook.authchatting.chat.message.exception;

public class NotFoundMessageException extends RuntimeException {
    public NotFoundMessageException() {
        super("해당하는 meesgae가 없습니다.");
    }
}
