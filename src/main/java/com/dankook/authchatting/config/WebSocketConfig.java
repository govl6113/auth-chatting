package com.dankook.authchatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.dankook.authchatting.chat.message.infra.in.web.socket.handler.WebSocketHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
        // 주소 : ws://localhost:8080/ws
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 구독자 -> 서버 (메세지보낼때)
        registry.setApplicationDestinationPrefixes("/pub");

        // 브로커 -> 구독자들 (메세지받을때)
        registry.enableSimpleBroker("/sub");      // In-Memory Message Broker 사용
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketHandler);
    }
}
