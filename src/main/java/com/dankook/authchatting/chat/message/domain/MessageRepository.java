package com.dankook.authchatting.chat.message.domain;

public interface MessageRepository {
    Message save(Message message);

    Message getById(Long id);
}
