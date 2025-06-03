package com.dankook.authchatting.chat.message.infra.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dankook.authchatting.chat.message.domain.Message;

public interface MessageJpaRepository extends JpaRepository<Message, Long> {

    List<Message> findByRoomId(Long roomId);
}
