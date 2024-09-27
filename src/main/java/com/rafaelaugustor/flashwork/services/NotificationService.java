package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Notification;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.NotificationType;
import com.rafaelaugustor.flashwork.repositories.NotificationRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.NotificationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;

    public void sendNotification(NotificationRequestDTO request, Principal principal) {
        User sender = userRepository.findByEmail(principal.getName());
        User receiver = userRepository.findById(request.getReceiver())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        Notification notification = Notification.builder()
                .content(request.getContent())
                .date(LocalDateTime.now())
                .isViewed(false)
                .notificationType(request.getType())
                .sender(sender)
                .receiver(receiver)
                .notificationType(NotificationType.ALERT)
                .build();

        notificationRepository.save(notification);

        messagingTemplate.convertAndSend("/topic/notifications/" + receiver.getId(), notification);
    }

    public List<Notification> getNotificationsForUser(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        return notificationRepository.findByReceiverId(user.getId());
    }

    public void markAsViewed(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        if (!notification.getIsViewed()){
            notification.setIsViewed(true);
            notificationRepository.save(notification);
        }
    }

    public void deleteNotification(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notificationRepository.delete(notification);
    }
}
