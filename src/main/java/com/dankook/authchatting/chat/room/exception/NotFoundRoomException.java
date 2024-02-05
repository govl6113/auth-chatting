package com.dankook.authchatting.chat.room.exception;

public class NotFoundRoomException extends RuntimeException {
    public NotFoundRoomException() {
        super("방 못찾음 ㅅㄱ");
    }
}
