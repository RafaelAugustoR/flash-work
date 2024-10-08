package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.enums.ProposalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalResponseDTO {
    private UUID id;
    private UUID serviceId;
    private UUID freelancerId;
    private ProposalStatus status;
    private String message;
    private Double offerAmount;
    private LocalDate estimatedCompletionTime;
    private Timestamp requestedAt;
    private Timestamp completedAt;
}
