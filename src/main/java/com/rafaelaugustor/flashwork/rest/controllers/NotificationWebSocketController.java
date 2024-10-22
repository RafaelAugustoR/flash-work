package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.NotificationRequestDTO;
import com.rafaelaugustor.flashwork.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class NotificationWebSocketController {
    private final NotificationService notificationService;

    @MessageMapping("/sendNotification")
    @SendTo("/topic/notifications/{receiverId}")
    public void sendNotification(NotificationRequestDTO request) {
        notificationService.sendNotification(request);
    }

}
