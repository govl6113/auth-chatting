package com.dankook.authchatting.chat.message.application;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dankook.authchatting.chat.message.domain.Message;
import com.dankook.authchatting.chat.message.domain.MessageRepository;
import com.dankook.authchatting.chat.message.infra.in.web.request.MessageType;
import com.dankook.authchatting.chat.message.infra.in.web.request.SendChatMessageRequest;
import com.dankook.authchatting.chat.message.infra.in.web.response.ChatMessageResponse;
import com.dankook.authchatting.chat.room.domain.Room;
import com.dankook.authchatting.chat.room.domain.RoomRepository;
import com.dankook.authchatting.member.domain.Member;
import com.dankook.authchatting.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate template;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Override
    public ChatMessageResponse send(
            Boolean sendTo,
            Long userId,
            Long roomId,
            SendChatMessageRequest request
    ) {
        Room room = roomRepository.getById(roomId);
        Member sender = memberRepository.getById(userId);

        String content = "";
        if (request.getType() == MessageType.ENTER) {
            content = sender.getName() + " 님이 입장하셨습니다.";
        }
        if (request.getType() == MessageType.EXIT) {
            content = sender.getName() + " 님이 퇴장하셨습니다.";
        }

        Message message = messageRepository.save(
                Message.builder()
                       .room(room)
                       .member(sender)
                       .content(request.getMessage().isEmpty() ? content : request.getMessage())
                       .build()
        );

        if (sendTo == Boolean.FALSE) {
            template.convertAndSend(
                    "/sub/chat/room/" + roomId,
                    message
            );
        }

        return ChatMessageResponse.fromEntity(message);
    }
}

