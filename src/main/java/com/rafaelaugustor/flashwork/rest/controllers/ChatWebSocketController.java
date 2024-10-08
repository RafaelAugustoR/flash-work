package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.domain.entities.Chat;
import com.rafaelaugustor.flashwork.domain.entities.Message;
import com.rafaelaugustor.flashwork.repositories.ChatRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.MessageRequestDTO;
import com.rafaelaugustor.flashwork.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class ChatWebSocketController {

    private final MessageService messageService;
    private final ChatRepository chatRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages/{chatId}")
    public Message sendMessage(MessageRequestDTO messageRequest) {

        Chat chat = chatRepository.findById(messageRequest.getChatId())
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));


        messagingTemplate.convertAndSend("/topic/messages/" + chat.getId(), messageRequest);

        return messageService.sendMessage(
                messageRequest.getUserId(),
                messageRequest.getChatId(),
                messageRequest.getContent()
        );
    }

}
