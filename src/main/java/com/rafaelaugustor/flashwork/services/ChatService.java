package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Chat;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.ChatRepository;
import com.rafaelaugustor.flashwork.repositories.MessageRepository;
import com.rafaelaugustor.flashwork.rest.dtos.response.ChatResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.MessageResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public void createChat(User userOne, User userTwo) {
        Optional<Chat> existingChat = chatRepository.findByUsers(userOne, userTwo);
        if (existingChat.isPresent()) {
            throw new RuntimeException("Chat already exists");
        }

        Chat chat = Chat.builder()
                .users(List.of(userOne, userTwo))
                .build();

        chatRepository.save(chat);
    }

    @Transactional
    public List<ChatResponseDTO> getChatsForUser(User user) {
        return user.getChats().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ChatResponseDTO findChatById(UUID chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));

        ChatResponseDTO chatResponseDTO = convertToDTO(chat);

        List<MessageResponseDTO> messages = messageRepository.findByChatOrderBySentAtAsc(chat).stream()
                .map(message -> new MessageResponseDTO(
                        message.getId(),
                        message.getContent(),
                        message.getSentAt().toString(),
                        message.getUser().getId(),
                        message.getUser().getName()))
                .toList();

        chatResponseDTO.setMessages(messages);

        return chatResponseDTO;
    }

    private ChatResponseDTO convertToDTO(Chat chat) {
        return new ChatResponseDTO(
                chat.getId(),
                chat.getUsers().stream()
                        .map(u -> new UserResponseDTO(u.getId(), u.getName(), u.getEmail()))
                        .toList()
        );
    }
}
