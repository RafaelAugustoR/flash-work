package com.rafaelaugustor.flashwork.rest.controllers;

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
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponseDTO> createChat(@RequestBody ChatRequestDTO request, Principal principal) {
        ChatResponseDTO response = chatService.create(request, principal);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<ChatResponseDTO>> findChatsByUserId(Principal principal) {
        return ResponseEntity.ok().body(chatService.findAllChatsByUser(principal));
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponseDTO> findChatById(@PathVariable UUID chatId) {
        ChatResponseDTO chatResponseDTO = chatService.findById(chatId);
        return ResponseEntity.ok(chatResponseDTO);
    }
}
