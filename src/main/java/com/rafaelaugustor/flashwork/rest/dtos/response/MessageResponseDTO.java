package com.rafaelaugustor.flashwork.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDTO {
    private UUID id;
    private String content;
    private String sentAt;
    private UUID userId;
    private String userName;
}
