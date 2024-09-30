package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Notification;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.NotificationType;
import com.rafaelaugustor.flashwork.repositories.NotificationRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.NotificationRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.NotificationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;

    public void sendNotification(NotificationRequestDTO request) {

        User sender = userRepository.findById(request.getSender())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
        User receiver = userRepository.findById(request.getReceiver())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        Notification notification = Notification.builder()
                .content(request.getContent())
                .date(LocalDateTime.now())
                .isViewed(false)
                .sender(sender)
                .receiver(receiver)
                .notificationType(NotificationType.ALERT)
                .build();

        notificationRepository.save(notification);

        messagingTemplate.convertAndSend("/topic/notifications/" + receiver.getId(), notification);
    }

    public List<NotificationResponseDTO> getNotificationsForUser(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        List<Notification> notifications = notificationRepository.findByReceiverId(user.getId());

        return notifications.stream()
                .map(notification -> new NotificationResponseDTO(
                        notification.getId(),
                        notification.getContent(),
                        notification.getSender().getId(),
                        notification.getIsViewed(),
                        notification.getDate(),
                        notification.getNotificationType()
                ))
                .collect(Collectors.toList());
    }

    public NotificationResponseDTO getNotificationById(UUID notificationId){

        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .content(notification.getContent())
                .isViewed(notification.getIsViewed())
                .date(notification.getDate())
                .notificationType(notification.getNotificationType())
                .senderId(notification.getSender().getId())
                .build();
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
