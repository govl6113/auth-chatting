package com.dankook.authchatting.chat.message.application;

import com.dankook.authchatting.chat.message.infra.in.web.request.SendChatMessageRequest;
import com.dankook.authchatting.chat.message.infra.in.web.response.ChatMessageResponse;

public interface MessageService {

    ChatMessageResponse send(
            Boolean sendTo,
            Long userId,
            Long roomId,
            SendChatMessageRequest request
    );
}
