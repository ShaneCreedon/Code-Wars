package com.codewars.application.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class SocketBrokerConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * The application destination prefix is an arbitrary prefix to
         * differentiate between messages that need to be routed to
         * message-handling methods for application level work vs messages to be
         * routed to the broker to broadcast to subscribed clients. After
         * application level work is finished the message can be routed to
         * broker for broadcasting.
         */
        registry.setApplicationDestinationPrefixes("/app");


        /*
         * The list of destination prefixes provided in this are based on what
         * broker is getting used. In this case we will use in-memory broker
         * which doesn't have any such requirements. For the purpose of
         * maintaining convention the "/topic" and the "/queue" prefixes are
         * chosen. The convention dictates usage of "/topic" destination for
         * pub-sub model targeting many subscribers and the "/queue" destination
         * for point to point messaging.
         */
        registry.enableSimpleBroker("/chat/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-app").setAllowedOrigins("*");
    }
}
