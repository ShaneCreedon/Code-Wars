package com.codewars.application.domain.entity;

import com.codewars.application.domain.enums.MessageType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatMessage {

    private final MessageType type;
    private final String content;
    private final String sender;
    private final String time;

}