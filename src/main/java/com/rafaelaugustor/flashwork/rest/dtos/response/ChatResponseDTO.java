package com.rafaelaugustor.flashwork.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponseDTO {
    private UUID chatId;
    private String createdAt;
    private List<UserResponseDTO> users;
    private List<MessageResponseDTO> messages;

    public ChatResponseDTO(UUID chatId, List<UserResponseDTO> users) {
        this.chatId = chatId;
        this.users = users;
    }
}
