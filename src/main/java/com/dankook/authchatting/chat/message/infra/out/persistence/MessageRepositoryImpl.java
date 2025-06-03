package com.dankook.authchatting.chat.message.infra.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dankook.authchatting.chat.message.domain.Message;
import com.dankook.authchatting.chat.message.domain.MessageRepository;
import com.dankook.authchatting.chat.message.exception.NotFoundMessageException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageJpaRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message getById(Long id) {
        return messageRepository.findById(id).orElseThrow(NotFoundMessageException::new);
    }

    public List<Message> getByRoomId(Long roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}
