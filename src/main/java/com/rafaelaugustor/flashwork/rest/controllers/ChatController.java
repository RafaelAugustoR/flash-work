package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.ChatRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ChatResponseDTO;
import com.rafaelaugustor.flashwork.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequiredArgsConstructor
@RequestMapping(APP_ROOT + "/chats")
@CrossOrigin("*")
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> createChat(@RequestBody ChatRequestDTO request, Principal principal) {
        User userOne = userRepository.findByEmail(principal.getName());
        User userTwo = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        chatService.createChat(userOne, userTwo);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ChatResponseDTO>> getChatsForUser(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        return ResponseEntity.ok().body(chatService.getChatsForUser(user));
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponseDTO> getChatById(@PathVariable UUID chatId) {
        ChatResponseDTO chatResponseDTO = chatService.findChatById(chatId);
        return ResponseEntity.ok(chatResponseDTO);
    }
}
