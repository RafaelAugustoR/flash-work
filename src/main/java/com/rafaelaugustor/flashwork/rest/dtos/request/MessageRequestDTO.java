package com.rafaelaugustor.flashwork.rest.dtos.request;

import com.rafaelaugustor.flashwork.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequestDTO {
    private UUID chatId;
    private UUID userId;
    private String content;
}
