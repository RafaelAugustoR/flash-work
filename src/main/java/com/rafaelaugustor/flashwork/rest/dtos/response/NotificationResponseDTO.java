package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {
    private UUID id;
    private String content;
    private UUID senderId;
    private Boolean isViewed;
    private LocalDateTime date;
    private NotificationType notificationType;
}
