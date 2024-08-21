package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.enums.ServiceRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestResponseDTO {
    private UUID id;
    private UUID serviceId;
    private UUID requesterId;
    private ServiceRequestStatus status;
    private String description;
    private Timestamp requestedAt;
    private Timestamp completedAt;
}
