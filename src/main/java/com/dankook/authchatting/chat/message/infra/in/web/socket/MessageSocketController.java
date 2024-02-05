package com.dankook.authchatting.chat.message.infra.in.web.socket;

import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.dankook.authchatting.auth.application.TokenProvider;
import com.dankook.authchatting.chat.message.application.MessageService;
import com.dankook.authchatting.chat.message.infra.in.web.request.SendChatMessageRequest;
import com.dankook.authchatting.chat.message.infra.in.web.response.ChatMessageResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageSocketController { //socket 용

    private final MessageService messageService;
    private final TokenProvider tokenProvider;

    // client에서 메세지 발행할 땐 "/pub/chat/room/{roomId}로 보내야함.
    @MessageMapping("/chat/room/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    public ChatMessageResponse handleMessageWithSendTo(
            // http header는 비어있고, 그 안의 socket header에 담겨있음.(network tab의 message영역에서 보임)
            // security: https://velog.io/@joypeb/Springboot3-Websocket-%EC%B1%84%ED%8C%85-Stomp-SockJS-Spring-Security
            @Valid @NotEmpty @Header(HttpHeaders.AUTHORIZATION) String authentication,
            @DestinationVariable("roomId") Long roomId,
            @Payload() SendChatMessageRequest payload
    ) {
        return messageService.send(
                true,
                tokenProvider.parseMemberId(resolveToken(authentication)),
                roomId,
                payload
        );
    }

    //     sendTo 없이 convertAndSend로 보내기
//    @MessageMapping("/chat/room/{roomId}") 위랑 같으니 잠시 주석
    public ChatMessageResponse handleMessageWithoutSendTo(
            @Valid @NotEmpty @Header(HttpHeaders.AUTHORIZATION) String authentication,
            @DestinationVariable("roomId") Long roomId,
            @Payload() SendChatMessageRequest payload
    ) {
        return messageService.send(
                false,
                tokenProvider.parseMemberId(resolveToken(authentication)),
                roomId,
                payload
        );
    }

    private String resolveToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    /*  sendTo대신 convertAndSent 사용
     *     // /pub/chat/enter 에 메세지가 오면 동작
     *     @MessageMapping(value = "/chat/enter")
     *     public void enter(ChatRequestDto message){ // 채팅방 입장
     *         message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
     *         template.convertAndSend("/sub/chat/" + message.getRoomId(), message);
     *     }
     *
     *     // /pub/chat/message 에 메세지가 오면 동작
     *     @MessageMapping(value = "/chat/message")
     *     public void message(ChatRequestDto message){
     *         ChatResponseDto savedMessage = chatService.saveMessage(message);
     *         template.convertAndSend("/sub/chat/" + savedMessage.getRoomId(), savedMessage);
     *     }
     *
     *     // /pub/chat/exit 에 메세지가 오면 동작
     *     @MessageMapping(value = "/exit/message")
     *
     *     test방법: apic
     *     https://velog.io/@hyewon0218/Stomp%EB%A1%9C-%EC%B1%84%ED%8C%85-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8-%EB%A7%8C%EB%93%A4%EA%B8%B0
     */

}

