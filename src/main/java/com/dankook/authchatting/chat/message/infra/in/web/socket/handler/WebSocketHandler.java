package com.dankook.authchatting.chat.message.infra.in.web.socket.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dankook.authchatting.auth.application.TokenProvider;
import com.dankook.authchatting.auth.exception.NotFoundAuthException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler implements ChannelInterceptor {

    private final String BEARER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;

    // handler : // https://medium.com/@shashank070/stomp-websocket-connection-with-server-side-authorization-via-aws-api-gateway-and-aws-cognito-9348f2bf946e
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION);
            if (!tokenProvider.validation(resolveToken(token))) {
                throw new NotFoundAuthException();
            }
        }
        return message;
    }

    private String resolveToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            return token.substring(7);
        }
        return null;
    }
}
