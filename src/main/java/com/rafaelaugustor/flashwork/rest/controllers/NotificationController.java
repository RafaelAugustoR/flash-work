package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.response.NotificationResponseDTO;
import com.rafaelaugustor.flashwork.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<NotificationResponseDTO>> findAllNotifications(Principal principal, Pageable pageable) {
        Page<NotificationResponseDTO> notifications = notificationService.findAllNotificationsByUser(principal, pageable);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> findNotificationById(@PathVariable UUID id) {
        NotificationResponseDTO notification = notificationService.findById(id);
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable UUID id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
