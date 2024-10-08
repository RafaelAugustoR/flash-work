package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Chat;
import com.rafaelaugustor.flashwork.domain.entities.Message;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.ChatRepository;
import com.rafaelaugustor.flashwork.repositories.MessageRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Transactional
    public Message sendMessage(UUID userId, UUID chatId, String content) {
        User foundedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));

        if (!chat.getUsers().contains(foundedUser)) {
            throw new IllegalArgumentException("User is not part of this chat");
        }

        Message message = new Message();
        message.setUser(foundedUser);
        message.setChat(chat);
        message.setContent(content);
        message.setSentAt(new Timestamp(System.currentTimeMillis()));

        return messageRepository.save(message);
    }
}