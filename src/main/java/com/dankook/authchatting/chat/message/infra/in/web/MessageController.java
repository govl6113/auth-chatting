package com.dankook.authchatting.chat.message.infra.in.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dankook.authchatting.chat.message.application.MessageService;
import com.dankook.authchatting.chat.message.infra.in.web.request.SendChatMessageRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    // rest는 잘 안 쓰지만 쉬운 테스트를 위해 사용
    @PostMapping("/{roomId}")
    public void send(@PathVariable Long roomId, Authentication authentication, SendChatMessageRequest request) {
        messageService.send(false, Long.parseLong(authentication.getName()), roomId, request);
    }

}
