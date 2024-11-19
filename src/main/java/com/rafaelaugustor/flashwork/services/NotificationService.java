package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.broker.producers.EmailProducer;
import com.rafaelaugustor.flashwork.domain.entities.Notification;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.NotificationType;
import com.rafaelaugustor.flashwork.repositories.NotificationRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.EmailRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.NotificationRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.NotificationResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserMinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final EmailProducer producer;
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

        EmailRequestDTO emailRequest = EmailRequestDTO.builder()
                .to(receiver.getEmail())
                .subject("You have a new notification!")
                .text(notification.getContent())
                .build();

        producer.sendEmail(emailRequest);

        messagingTemplate.convertAndSend("/topic/notifications/" + receiver.getId(), notification);
    }


    public Page<NotificationResponseDTO> findAllNotificationsByUser(Principal principal, Pageable pageable) {
        User user = userRepository.findByEmail(principal.getName());

        Page<Notification> notificationsPage = notificationRepository.findByReceiverId(user.getId(), pageable);

        return notificationsPage.map(this::toResponseDTO);
    }

    public NotificationResponseDTO findById(UUID notificationId){

        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        if (!notification.getIsViewed()) {
            notification.setIsViewed(true);
            notificationRepository.save(notification);
        }

        return toResponseDTO(notification);
    }

    public void delete(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notificationRepository.delete(notification);
    }

    private NotificationResponseDTO toResponseDTO(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getContent(),
                new UserMinDTO(notification.getSender()),
                notification.getIsViewed(),
                notification.getDate(),
                notification.getNotificationType()
        );
    }

}
