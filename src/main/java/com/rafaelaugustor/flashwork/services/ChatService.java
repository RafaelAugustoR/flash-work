package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Chat;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.ChatRepository;
import com.rafaelaugustor.flashwork.repositories.MessageRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.ChatRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ChatResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.MessageResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserMinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatResponseDTO create(ChatRequestDTO request, Principal principal) {

        User userOne = userRepository.findByEmail(principal.getName());
        User userTwo = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        if (chatRepository.findByUsers(userOne, userTwo).isPresent()) {
            throw new RuntimeException("Chat already exists");
        }

        Chat chat = Chat.builder()
                .users(List.of(userOne, userTwo))
                .build();

        chatRepository.save(chat);

        List<UserMinDTO> userResponseDTOs = chat.getUsers().stream()
                .map(user -> UserMinDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build())
                .collect(Collectors.toList());

        return ChatResponseDTO.builder()
                .users(userResponseDTOs)
                .build();
    }

    @Transactional
    public List<ChatResponseDTO> findAllChatsByUser(Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName());

        List<Chat> chats = chatRepository.findAllByUsersContains(currentUser);

        return chats.stream()
                .map(chat -> {
                    List<UserMinDTO> otherUsers = chat.getUsers().stream()
                            .filter(user -> !user.getId().equals(currentUser.getId()))
                            .map(user -> new UserMinDTO(user.getId(), user.getName(), user.getProfilePicture(), user.getDescription()))
                            .toList();

                    return new ChatResponseDTO(
                            chat.getId(),
                            otherUsers
                    );
                })
                .toList();
    }

    public ChatResponseDTO findById(UUID chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));

        ChatResponseDTO chatResponseDTO = toResponseDTO(chat);

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

    private ChatResponseDTO toResponseDTO(Chat chat) {
        return new ChatResponseDTO(
                chat.getId(),
                chat.getUsers().stream()
                        .map(u -> new UserMinDTO(u.getId(), u.getName(), u.getProfilePicture(), u.getUsername()))
                        .toList()
        );
    }
}
