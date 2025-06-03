package com.dankook.authchatting.chat.message.infra.in.web.response;

import java.time.LocalDateTime;

import com.dankook.authchatting.chat.message.domain.Message;
import com.dankook.authchatting.chat.room.domain.Room;
import com.dankook.authchatting.member.domain.Member;

import lombok.Getter;

@Getter
public class ChatMessageResponse {
    public static ChatMessageResponse fromEntity(Message message) {
        return new ChatMessageResponse(message.getId().toString(),
                                       message.getCreatedAt(),
                                       message.getSender(),
                                       message.getRoom(),
                                       message.getContent());
    }

    private final String id;
    private final LocalDateTime createdAt;
    private final Member sender;
    private final Room room;
    private final String message;

    public ChatMessageResponse(String id, LocalDateTime createdAt, Member sender, Room room, String message) {
        this.id = id;
        this.createdAt = createdAt;
        this.sender = sender;
        this.room = room;
        this.message = message;
    }
}
