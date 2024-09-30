package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.response.ChatResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.NotificationResponseDTO;
import com.rafaelaugustor.flashwork.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/notifications")
@RequiredArgsConstructor
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getNotifications(Principal principal) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsForUser(principal);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/{id}/viewed")
    public ResponseEntity<Void> markAsViewed(@PathVariable UUID id) {
        notificationService.markAsViewed(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponseDTO> getChatById(@PathVariable UUID notificationId) {
        NotificationResponseDTO notification = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable UUID id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
